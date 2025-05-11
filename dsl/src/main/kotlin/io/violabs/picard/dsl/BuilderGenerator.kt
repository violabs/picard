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
    val propTypeName: TypeName
    val nullable: Boolean get() = true
    val verifyNotNull: Boolean get() = true
    val verifyNotEmpty: Boolean get() = false
    fun toPropertySpec(): PropertySpec
    fun accessors(): List<FunSpec> {
        return emptyList()
    }

    fun propertyValueReturn(): String {
        if (nullable) return propName

        return if (verifyNotNull) {
            "vRequireNotNull(::$propName)"
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
    override val propTypeName: TypeName = BOOLEAN.copy(nullable)

    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .addModifiers(KModifier.PRIVATE)
        .mutable(true)
        .initializer("null")
        .build()

    override fun accessors(): List<FunSpec> {
        val param = ParameterSpec
            .builder("on", propTypeName.copy(nullable = false))
            .defaultValue("true")
            .build()

        return FunSpec.builder(propName)
            .addParameter(param)
            .addStatement("this.%N = %N", propName, param)
            .build()
            .let { listOf(it) }
    }
}

class DefaultParam(
    override val propName: String,
    override val propTypeName: TypeName = STRING,
    override val nullable: Boolean = true
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

class BuilderParam(
    override val propName: String,
    override val propTypeName: TypeName,
    override val nullable: Boolean = true
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

class ListParam(
    override val propName: String,
    override val propTypeName: TypeName = LIST.parameterizedBy(STRING),
    override val nullable: Boolean = true
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()

    override val verifyNotNull: Boolean = false
    override val verifyNotEmpty: Boolean = true
}

class GroupParam(
    override val propName: String,
    override val propTypeName: TypeName,
    override val nullable: Boolean = true
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

private val DEFAULT_TYPES = listOf(BOOLEAN, STRING, INT, LONG, SHORT, BYTE, DOUBLE, FLOAT)
private val DEFAULT_TYPE_NAMES = DEFAULT_TYPES.map { it.simpleName }

fun determineParam(prop: KSPropertyDeclaration): DSLParam {
    val propClassName = prop.type.resolve().toClassName().simpleName
    val propName = prop.simpleName.asString()
    val propType = prop.type.toTypeName()
    val propTypeName = prop.type.toTypeName().copy(nullable = true)

    val isNullable = propType.isNullable

    return when {
        propClassName == BOOLEAN.simpleName -> BooleanParam(propName, isNullable)
        propClassName in DEFAULT_TYPE_NAMES -> DefaultParam(propName, propTypeName, isNullable)
        LIST.simpleName in propClassName -> ListParam(propName, propTypeName, isNullable)
        "Group" in propClassName -> GroupParam(propName, propTypeName, isNullable)
        else -> BuilderParam(propName, propTypeName, isNullable)
    }
}

class BuilderGenerator(val logger: KSPLogger) {

    fun generate(resolver: Resolver, codeGenerator: CodeGenerator, options: Map<String, String> = emptyMap()) {
        val dslBuilderClasspath = options["dslBuilder.classpath"]

        if (dslBuilderClasspath == null) {
            logger.error("KSP Option 'dslBuilder.classpath' is not defined. Please set it in your build.gradle.")
            // Handle missing option
            return
        }

        val annotated = resolver
            .getSymbolsWithAnnotation(GenerateDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        annotated.forEach { domain ->
            val pkg = domain.packageName.asString()
            val typeName = domain.simpleName.asString()
            val builderName = "${typeName}Builder"
            // <-- convert the KSClassDeclaration into a ClassName once
            val domainClassName: ClassName = domain.toClassName()

            // 1) build the Builder class
            val builderClass: TypeSpec.Builder = TypeSpec.classBuilder(builderName)

            val dslBuilderInterface = ClassName(dslBuilderClasspath, "DSLBuilder")

            val parameterizedDslBuilder = dslBuilderInterface.parameterizedBy(domainClassName)

            builderClass.addSuperinterface(parameterizedDslBuilder)

            // 2) build the `build()` function returning the real class
            val buildFun = FunSpec.builder("build")
                .addModifiers(KModifier.OVERRIDE)
                .returns(domainClassName)

            // 3) for each property, add a var + setter + required check
            val buildVars: List<DSLParam> = domain.getAllProperties().map { prop ->
                val dslParam = determineParam(prop)

                // a) backing var
                builderClass.addProperty(dslParam.toPropertySpec())

                // b) DSL setter
                dslParam.accessors().forEach {
                    builderClass.addFunction(it)
                }

                dslParam
            }.toList()

            val buildFn = generateInstanceCreationFunction("build", domainClassName, buildVars, KModifier.OVERRIDE)

            buildFun.addStatement("return %T(%L)", domainClassName, buildVars)
            builderClass.addFunction(buildFn)

            println("-----HERE")

            val anyRequiredNotNull = buildVars.any { !it.nullable && it.verifyNotNull }
            val anyRequiredNotEmpty = buildVars.any { !it.nullable && it.verifyNotEmpty }

            // 6) write into a file named `${TypeName}Dsl.kt`
            val fileSpec = FileSpec
                .builder(pkg, "${typeName}Dsl")
                .addType(builderClass.build())
                .indent("    ")

            if (anyRequiredNotNull) {
                fileSpec.addImport("io.violabs.picard.starCharts.common", "vRequireNotNull")
            }

            if (anyRequiredNotEmpty) {
                fileSpec.addImport("io.violabs.picard.starCharts.common", "vRequireNotEmpty")
            }

            fileSpec
                .build()
                .writeTo(codeGenerator, Dependencies(false))
        }
    }
}

/**
 * Generates a function that creates an instance of [returnClassType]
 * using named arguments from [constructorArgs].
 *
 * @param functionName The name of the function to generate.
 * @param returnClassType The TypeName of the class to be instantiated and returned.
 * @param constructorArgs A list of pairs, where each pair is (propertyName: String, propertyValue: Any?).
 * The propertyValue will be treated as a literal.
 * @return A FunSpec for the generated function.
 */
private fun generateInstanceCreationFunction(
    functionName: String,
    returnClassType: TypeName,
    constructorArgs: List<DSLParam>,
    modifier: KModifier = KModifier.PUBLIC,
): FunSpec {
    val funBuilder = FunSpec.builder(functionName)
        .returns(returnClassType)

    if (constructorArgs.isEmpty()) {
        // Case: No constructor arguments, e.g., return MyData()
        funBuilder.addStatement("return %T()", returnClassType)
    } else {
        // Case: Constructor with arguments
        // 1. Create the block of "propertyName = propertyValue" assignments
        val argumentsBlock = constructorArgs.map { param ->
            // %N is for the name (property name)
            // %L is for the literal value
            CodeBlock.of("\t%N = %L", param.propName, param.propertyValueReturn())
        }.joinToCode(separator = ",\n") // Join with comma and newline for readability

        // 2. Add the return statement, embedding the argumentsBlock
        // The \n%L\n ensures the arguments are on new lines and indented (by KotlinPoet's default formatting for statements)
        funBuilder.addStatement("return %T(\n%L\n)", returnClassType, argumentsBlock)
    }

    return funBuilder.addModifiers(modifier).build()
}
