package io.violabs.picard.dsl

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

class DSLProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotated = resolver
            .getSymbolsWithAnnotation(GenerateDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        annotated.forEach { domain ->
            val pkg = domain.packageName.asString()
            val typeName = domain.simpleName.asString()
            val builderName = "${typeName}Builder"

            val builderClass = TypeSpec.classBuilder(builderName)
            val buildFun = FunSpec.builder("build")
                .returns(domain.asType(emptyList()).toTypeName())

            // Every property becomes a var in the builder + a DSL setter
            domain.getAllProperties().forEach { prop ->
                val name = prop.simpleName.asString()
                val kType = prop.type.toTypeName().copy(nullable = true)
                builderClass.addProperty(
                    PropertySpec.builder(name, kType)
                        .mutable(true)
                        .initializer("null")
                        .build()
                )
                builderClass.addFunction(
                    FunSpec.builder(name)
                        .addParameter(name, kType)
                        .addStatement("this.%N = %N", name, name)
                        .addStatement("return this")
                        .build()
                )
                buildFun.addStatement(
                    "%N ?: error(%S)",
                    name,
                    "$name required for $typeName DSL"
                )
            }

            val args = domain.getAllProperties()
                .joinToString { it.simpleName.asString() }

            buildFun.addStatement("return %T($args)", domain)

            builderClass.addFunction(buildFun.build())

            val dslFun = FunSpec.builder(typeName.replaceFirstChar(Char::lowercase))
                .addParameter(
                    "block",
                    LambdaTypeName.get(
                        receiver = ClassName(pkg, builderName),
                        returnType = UNIT
                    )
                )
                .returns(domain.asType(emptyList()).toTypeName())
                .addStatement("return %T().apply(block).build()", ClassName(pkg, builderName))
                .build()

            val file = FileSpec.builder(pkg, "${typeName}Dsl")
                .addType(builderClass.build())
                .addFunction(dslFun)
                .build()

            file.writeTo(codeGenerator, Dependencies(false))
        }
        return emptyList()
    }
}
