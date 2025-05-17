package io.violabs.picard.dsl

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

@AutoService(SymbolProcessorProvider::class)
class DSLProvider : SymbolProcessorProvider {
    override fun create(env: SymbolProcessorEnvironment) =
        DSLProcessor(env.codeGenerator, env.options)
}
