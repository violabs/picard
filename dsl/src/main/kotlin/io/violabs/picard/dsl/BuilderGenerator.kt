package io.violabs.picard.dsl

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

interface DSLParam {
    val propName: String
    val propTypeName: TypeName // This should be the type of the actual property in the domain object
    val nullable: Boolean get() = true
    val verifyNotNull: Boolean get() = true
    val verifyNotEmpty: Boolean get() = false

    fun toPropertySpec(): PropertySpec

    // Added containingBuilderClassName to allow fluent return types
    fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        return emptyList()
    }

    fun propertyValueReturn(): String {
        if (nullable) return propName

        return if (verifyNotNull) {
            "vRequireNotNull(::$propName, \"$propName\")" // Added message for vRequireNotNull
        } else if (verifyNotEmpty) {
            "vRequireNotEmpty(::$propName, \"$propName\")"
        } else {
            propName
        }
    }
}

class BooleanParam(
    override val propName: String,
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = BOOLEAN.copy(nullable = nullable) // Correctly use constructor arg

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE)
        .mutable(true)
        .initializer("null")
        .build()

    override fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        val param = ParameterSpec
            .builder("on", BOOLEAN) // Non-nullable for the setter
            .defaultValue("true")
            .build()

        return FunSpec.builder(propName)
            .addParameter(param)
            .addStatement("this.%N = %N", propName, param)
            .addStatement("return this") // Fluent API
            .returns(containingBuilderClassName) // Return the builder itself
            .build()
            .let { listOf(it) }
    }
}

class DefaultParam(
    override val propName: String,
    actualPropTypeName: TypeName, // Use a more descriptive name
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullable)

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE) // Good to make backing fields private
        .mutable(true)
        .initializer("null")
        .build()

    // Example of a simple setter for DefaultParam if needed for fluency
    override fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        return FunSpec.builder(propName)
            .addParameter(propName, propTypeName.copy(nullable = false)) // Parameter should match property type (or be non-nullable version)
            .addStatement("this.%N = %N", propName, propName)
            .addStatement("return this")
            .returns(containingBuilderClassName)
            .build()
            .let { listOf(it) }
    }
}

class BuilderParam(
    override val propName: String,
    private val originalPropertyType: TypeName, // e.g., BloomBuildPlannerQueue?
    private val nestedBuilderClassName: ClassName, // e.g., BloomBuildPlannerQueueBuilder
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType // Type of the field in the builder

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE)
        .mutable(true)
        .initializer("null")
        .build()

    override fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        val blockParam = ParameterSpec.builder("block", LambdaTypeName.get(
            receiver = nestedBuilderClassName,  // THIS IS THE KEY: uses the specific builder type
            parameters = emptyList(),
            returnType = UNIT
        )).build()

        val funSpec = FunSpec.builder(propName) // Setter method name
            .addParameter(blockParam)
            .addStatement("val builder = %T()", nestedBuilderClassName) // Instantiates the specific builder
            .addStatement("builder.block()")
            .addStatement("this.%N = builder.build()", propName) // Assigns the built object
            .build()

        return listOf(funSpec)
    }
}

class ListParam(
    override val propName: String,
    actualPropTypeName: TypeName = LIST.parameterizedBy(STRING), // More descriptive name
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullable)

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE) // Good to make backing fields private
        .mutable(true)
        .initializer("null")
        .build()

    override val verifyNotNull: Boolean = false
    override val verifyNotEmpty: Boolean = true

    // Example for a list setter (could be more sophisticated, e.g., vararg)
    override fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        return FunSpec.builder(propName)
            .addParameter(propName, propTypeName.copy(nullable = false)) // Usually a non-null list
            .addStatement("this.%N = %N", propName, propName)
            .addStatement("return this")
            .returns(containingBuilderClassName)
            .build()
            .let { listOf(it) }
    }
}

// Assuming GroupParam is similar to BuilderParam or a complex type needing its own builder
class GroupParam(
    override val propName: String,
    private val originalPropertyType: TypeName, // e.g., MyGroupType?
    private val groupBuilderClassName: ClassName, // e.g., MyGroupTypeBuilder
    override val nullable: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE)
        .mutable(true)
        .initializer("null")
        .build()

    override fun accessors(containingBuilderClassName: ClassName): List<FunSpec> {
        val blockParam = ParameterSpec.builder("block", LambdaTypeName.get(
            receiver = groupBuilderClassName,
            parameters = emptyList(),
            returnType = UNIT
        )).build()

        val funSpec = FunSpec.builder(propName)
            .addParameter(blockParam)
            .addStatement("val builder = %T()", groupBuilderClassName)
            .addStatement("builder.block()")
            .addStatement("this.%N = builder.build()", propName)
            .addStatement("return this")
            .returns(containingBuilderClassName)
            .build()
        return listOf(funSpec)
    }
}

private val DEFAULT_TYPE_CANONICAL_NAMES = listOf(
    BOOLEAN, STRING, INT, LONG, SHORT, BYTE, DOUBLE, FLOAT
).map { it.canonicalName }

// Pass logger for potential warnings/errors
fun determineParam(prop: KSPropertyDeclaration, logger: KSPLogger): DSLParam {
    val propName = prop.simpleName.asString()
    // Get the TypeName for the property as it's declared (respecting its nullability)
    val actualPropertyType = prop.type.toTypeName()
    val isNullable = actualPropertyType.isNullable

    // For deriving builder names, we need the non-nullable ClassName of the type
    val resolvedType = prop.type.resolve()
    val propertyClassDeclaration = resolvedType.declaration as? KSClassDeclaration
    val propertyNonNullableClassName = propertyClassDeclaration?.toClassName() // Might be null if not a class (e.g. typealias to primitive)

    // Use canonicalName for robust comparison, especially with nullable types
    val canonicalName = actualPropertyType.copy(nullable = false).toString() // Get base type string

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

        annotated.forEach { domain ->
            val pkg = domain.packageName.asString()
            val typeName = domain.simpleName.asString()
            val builderName = "${typeName}Builder"
            val domainClassName: ClassName = domain.toClassName()
            val currentGeneratedBuilderClassName = ClassName(pkg, builderName) // ClassName of the builder we are generating

            val builderClass: TypeSpec.Builder = TypeSpec.classBuilder(builderName)
                .addModifiers(KModifier.PUBLIC) // Typically builders are public

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
                val dslParam = determineParam(prop, logger) // Pass logger

                builderClass.addProperty(dslParam.toPropertySpec())

                dslParam.accessors(currentGeneratedBuilderClassName).forEach { // Pass the current builder's ClassName
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
                val argumentsBlock = constructorParams.joinToCode(separator = ",\n    ", prefix = "\n    ", suffix = "\n")
                buildFun.addStatement("return %T(%L)", domainClassName, argumentsBlock)
            }
            builderClass.addFunction(buildFun.build())


            // Check if any validation functions are needed.
            // This logic assumes propertyValueReturn() generates the vRequireNotNull etc. calls.
            val requiresVRequireNotNull = domain.getAllProperties().any { prop ->
                val dslP = determineParam(prop, logger)
                !dslP.nullable && dslP.verifyNotNull
            }
            val requiresVRequireNotEmpty = domain.getAllProperties().any { prop ->
                val dslP = determineParam(prop, logger)
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
                .writeTo(codeGenerator, Dependencies(aggregating = false, sources = listOfNotNull(domain.containingFile).toTypedArray()))
        }
    }
}

// Removed generateInstanceCreationFunction as its logic is now inline in the main generate function
// for better clarity with constructorParams and dslParam.propertyValueReturn()