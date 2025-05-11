package io.violabs.picard.dsl

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

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

                // a) backing var
                builderClass.addProperty(
                    PropertySpec.builder(propName, propTypeName)
                        .mutable(true)
                        .initializer("null")
                        .build()
                )

                // b) DSL setter
                builderClass.addFunction(
                    FunSpec.builder(propName)
                        .addParameter(propName, propTypeName)
                        .addStatement("this.%N = %N", propName, propName)
                        .build()
                )

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
                .addFunction(dslFun)
                .build()
                .writeTo(codeGenerator, Dependencies(false))
        }
    }
}