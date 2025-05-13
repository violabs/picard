package io.violabs.picard.dsl

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import io.violabs.picard.dsl.annotation.GenerateDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL
import io.violabs.picard.dsl.param.*

private val DEFAULT_TYPE_CANONICAL_NAMES = listOf(
    BOOLEAN, STRING, INT, LONG, SHORT, BYTE, DOUBLE, FLOAT
).map { it.canonicalName }

// Pass logger for potential warnings/errors
fun determineParam(
    prop: KSPropertyDeclaration,
    singleEntryTransform: KSClassDeclaration?,
    logger: KSPLogger
): DSLParam {
    val propName = prop.simpleName.asString()
    // Get the TypeName for the property as it's declared (respecting its nullability)
    val actualPropertyType = prop.type.toTypeName()
    val isNullable = actualPropertyType.isNullable

    // For deriving builder names, we need the non-nullable ClassName of the type
    val resolvedType = prop.type.resolve()
    val propertyClassDeclaration = resolvedType.declaration as? KSClassDeclaration
    val propertyNonNullableClassName =
        propertyClassDeclaration?.toClassName() // Might be null if not a class (e.g. typealias to primitive)

    // Use canonicalName for robust comparison, especially with nullable types
    val canonicalName = actualPropertyType.copy(nullable = false).toString() // Get base type string

    if (singleEntryTransform != null) {
        val annotation = singleEntryTransform
            .annotations
            .find { it.shortName.asString() == SingleEntryTransformDSL::class.simpleName }
            ?: return throw IllegalArgumentException("SingleEntryTransform annotation not found on class ${singleEntryTransform.simpleName.asString()}")

        val transformTemplate = annotation
            .arguments
            .firstOrNull { it.name?.asString() == "transformTemplate" }
            ?.toString()

        val transformType = annotation
            .arguments
            .firstOrNull { it.name?.asString() == "inputType" }
            ?.value as KSType
            ?: throw IllegalArgumentException("transformTemplate requires a inputType argument")

        // Create a DSLParam that uses the transformer
        return SingleTransformParam(
            propName = prop.simpleName.asString(),
            transformTemplate = transformTemplate,
            actualPropTypeName = prop.type.toTypeName(),
            inputTypeName = transformType.toTypeName(),
            nullable = prop.type.resolve().isMarkedNullable
        )
    }

    return when {
        BOOLEAN.canonicalName == resolvedType.toClassName().canonicalName -> BooleanParam(propName, isNullable)
        DEFAULT_TYPE_CANONICAL_NAMES.contains(resolvedType.toClassName().canonicalName) ->
            DefaultParam(propName, actualPropertyType, isNullable)

        resolvedType.toClassName().canonicalName == LIST.canonicalName ->
            ListParam(propName, actualPropertyType, isNullable)
        // Assuming "Group" types also have builders following the "TypeNameBuilder" convention
        propertyNonNullableClassName != null && "Group" in propertyNonNullableClassName.simpleName -> {
            val groupBuilderName = propertyNonNullableClassName.simpleName + "Builder"
            val groupBuilderClassName = ClassName(propertyNonNullableClassName.packageName, groupBuilderName)
            GroupParam(propName, actualPropertyType, groupBuilderClassName, isNullable)
        }
        // Default to BuilderParam for other class types, assuming they follow the convention
        // or are meant to be configured via a builder.
        propertyNonNullableClassName != null -> {
            val nestedBuilderName = propertyNonNullableClassName.simpleName + "Builder"
            // The nested builder will be in the same package as its corresponding domain class
            val nestedBuilderClassName = ClassName(propertyNonNullableClassName.packageName, nestedBuilderName)
            BuilderParam(propName, actualPropertyType, nestedBuilderClassName, isNullable)
        }
        // Fallback for types not covered (e.g. functional types, type aliases not resolving to classes)
        else -> {
            logger.warn("Property '$propName' of type '${actualPropertyType}' could not be mapped to a known DSLParam type. Using DefaultParam as a fallback.")
            DefaultParam(propName, actualPropertyType, isNullable)
        }
    }
}

class BuilderGenerator(val logger: KSPLogger) {

    fun generate(resolver: Resolver, codeGenerator: CodeGenerator, options: Map<String, String> = emptyMap()) {
        val dslBuilderClasspath = options["dslBuilder.classpath"]
        val dslMarkerClasspath = options["dslMarker.classpath"]

        if (dslBuilderClasspath == null) {
            logger.error("KSP Option 'dslBuilder.classpath' is not defined. Please set it in your build.gradle.")
            return
        }

        val annotated = resolver
            .getSymbolsWithAnnotation(GenerateDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        val singleEntryTransform: Map<String, KSClassDeclaration> = resolver
            .getSymbolsWithAnnotation(SingleEntryTransformDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .associateBy { it.simpleName.asString() }

        annotated.forEach { domain ->
            val pkg = domain.packageName.asString()
            val typeName = domain.simpleName.asString()
            val builderName = "${typeName}Builder"
            val domainClassName: ClassName = domain.toClassName()

            val builderClass: TypeSpec.Builder = TypeSpec.classBuilder(builderName)
                .addModifiers(KModifier.PUBLIC) // Typically builders are public

            // add DSL Marker to the top of the class to restrict scope. Provided by consumer.
            if (dslMarkerClasspath != null) {
                println("Adding DSL Marker to $builderName")
                val split = dslMarkerClasspath.split(".")
                val dslMarkerPackageName = split.subList(0, split.size - 1).joinToString(".")
                val dslMarkerSimpleName = split.last()
                builderClass.addAnnotation(ClassName(dslMarkerPackageName, dslMarkerSimpleName))
            }

            val dslBuilderInterface = ClassName(dslBuilderClasspath, "DSLBuilder")
            val parameterizedDslBuilder = dslBuilderInterface.parameterizedBy(domainClassName)
            builderClass.addSuperinterface(parameterizedDslBuilder)

            val constructorParams = mutableListOf<CodeBlock>()

            domain.getAllProperties().forEach { prop ->
                val hasSingleEntryTransform = singleEntryTransform[prop.simpleName.asString()]

                val dslParam = determineParam(prop, hasSingleEntryTransform, logger) // Pass logger

                builderClass.addProperty(dslParam.toPropertySpec())

                dslParam.accessors().forEach { // Pass the current builder's ClassName
                    builderClass.addFunction(it)
                }
                // Prepare for the build() method's constructor call
                constructorParams.add(CodeBlock.of("%N = %L", dslParam.propName, dslParam.propertyValueReturn()))
            }

            val buildFun = FunSpec.builder("build")
                .addModifiers(KModifier.OVERRIDE)
                .returns(domainClassName)

            if (constructorParams.isEmpty()) {
                buildFun.addStatement("return %T()", domainClassName)
            } else {
                // joinToCode is cleaner for multi-line constructor calls
                val argumentsBlock =
                    constructorParams.joinToCode(separator = ",\n    ", prefix = "\n    ", suffix = "\n")
                buildFun.addStatement("return %T(%L)", domainClassName, argumentsBlock)
            }
            builderClass.addFunction(buildFun.build())


            // Check if any validation functions are needed.
            // This logic assumes propertyValueReturn() generates the vRequireNotNull etc. calls.
            val requiresVRequireNotNull = domain.getAllProperties().any { prop ->
                val dslP = determineParam(prop, null, logger)
                !dslP.nullable && dslP.verifyNotNull
            }
            val requiresVRequireNotEmpty = domain.getAllProperties().any { prop ->
                val dslP = determineParam(prop, null, logger)
                !dslP.nullable && dslP.verifyNotEmpty
            }

            val fileSpecBuilder = FileSpec
                .builder(pkg, "${typeName}Dsl") // File name
                .addType(builderClass.build())
                .indent("    ")

            if (requiresVRequireNotNull) {
                // Assuming these are top-level functions or extension functions
                fileSpecBuilder.addImport("io.violabs.picard.starCharts.common", "vRequireNotNull")
            }
            if (requiresVRequireNotEmpty) {
                fileSpecBuilder.addImport("io.violabs.picard.starCharts.common", "vRequireNotEmpty")
            }

            fileSpecBuilder
                .build()
                .writeTo(
                    codeGenerator,
                    Dependencies(aggregating = false, sources = listOfNotNull(domain.containingFile).toTypedArray())
                )
        }
    }
}