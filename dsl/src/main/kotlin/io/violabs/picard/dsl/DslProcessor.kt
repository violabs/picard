package io.violabs.picard.dsl

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.process.DefaultDslGenerator

private val LOGGER = Logger("ZONEX")
    .enableDebug()
    .disableWarning()

class DslProcessor(
    private val codeGenerator: CodeGenerator,
    private val options: Map<String, String>
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        DefaultDslGenerator().generate(resolver, codeGenerator, options)

        return emptyList()
    }
}