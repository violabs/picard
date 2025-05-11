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
    fun toPropertySpec(): PropertySpec
    fun accessors(): List<FunSpec> { return emptyList() }
}

class BooleanParam(
    override val propName: String,
    nullable: Boolean = true
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
    override val propTypeName: TypeName = STRING
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

class BuilderParam(
    override val propName: String,
    override val propTypeName: TypeName
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

class ListParam(
    override val propName: String,
    override val propTypeName: TypeName = LIST.parameterizedBy(STRING)
) : DSLParam {
    override fun toPropertySpec(): PropertySpec = PropertySpec.builder(propName, propTypeName)
        .mutable(true)
        .initializer("null")
        .build()
}

class GroupParam(
    override val propName: String,
    override val propTypeName: TypeName
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
    val propTypeName = prop.type.toTypeName().copy(nullable = true)

    return when {
        propClassName == BOOLEAN.simpleName -> BooleanParam(propName)
        propClassName in DEFAULT_TYPE_NAMES -> DefaultParam(propName, propTypeName)
        LIST.simpleName in propClassName -> ListParam(propName, propTypeName)
        "Group" in propClassName -> GroupParam(propName, propTypeName)
        else -> BuilderParam(propName, propTypeName)
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
            domain.getAllProperties().forEach { prop ->
                val propName = prop.simpleName.asString()
                val propTypeName = prop.type.toTypeName().copy(nullable = true)

                val dslParam = determineParam(prop)

                val accessor = if (prop.type.resolve().declaration.simpleName.asString().contains("Boolean"))
                    KModifier.PRIVATE
                else
                    KModifier.PUBLIC

                // a) backing var
                builderClass.addProperty(dslParam.toPropertySpec())

                // b) DSL setter
                dslParam.accessors().forEach {
                    builderClass.addFunction(it)
                }
//                builderClass.addFunction(
//                    FunSpec.builder(propName)
//                        .addParameter(propName, propTypeName)
//                        .addStatement("this.%N = %N", propName, propName)
//                        .build()
//                )

                // c) require-not-null in build()
                buildFun.addStatement(
                    "%N ?: error(%S)",
                    propName,
                    "$propName required for $typeName DSL"
                )
            }

            // 4) assemble the constructor args and return
            val args = domain.getAllProperties()
                .joinToString(", ") { it.simpleName.asString() }

            buildFun.addStatement("return %T($args)", domainClassName)
            builderClass.addFunction(buildFun.build())

            // 5) top-level DSL function
            val dslFunName = typeName.replaceFirstChar(Char::lowercase)
            val dslFun = FunSpec.builder(dslFunName)
                .receiver(domainClassName)
                .addParameter(
                    "block",
                    LambdaTypeName.get(
                        receiver = ClassName(pkg, builderName),
                        returnType = UNIT
                    )
                )
                .returns(domainClassName)
                .addStatement("return %TBuilder().apply(block).build()", domainClassName)
                .build()

            // 6) write into a file named `${TypeName}Dsl.kt`
            FileSpec.builder(pkg, "${typeName}Dsl")
                .addType(builderClass.build())
//                .addFunction(dslFun)
                .build()
                .writeTo(codeGenerator, Dependencies(false))
        }
    }
}