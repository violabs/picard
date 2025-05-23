package io.violabs.picard.dsl.process

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toClassName
import io.violabs.picard.metaDsl.builder.kotlinPoet
import io.violabs.picard.metaDsl.config.BuilderConfig

interface RootFunctionGenerator {
    fun generate(
        domain: KSClassDeclaration,
        builderConfig: BuilderConfig
    ): FunSpec
}

class DefaultRootFunctionGenerator : RootFunctionGenerator {
    override fun generate(
        domain: KSClassDeclaration,
        builderConfig: BuilderConfig
    ): FunSpec = kotlinPoet {
        function {
            val domainClassName = domain.toClassName()
            val domainBuilderClassName =
                ClassName(domainClassName.packageName, "${domainClassName.simpleName}DslBuilder")
            funName = domain.simpleName.asString().replaceFirstChar { it.lowercase() }
            // todo: add docs

            param {
                lambdaType {
                    receiver = domainBuilderClassName
                }
            }

            returns = domainClassName

            statements {
                addLine("val builder = %T()", domainBuilderClassName)
                addLine("builder.block()")
                addLine("return builder.build()")
            }
        }
    }
}