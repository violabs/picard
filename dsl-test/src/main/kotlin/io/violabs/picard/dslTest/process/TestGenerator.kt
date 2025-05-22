package io.violabs.picard.dslTest.process

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.violabs.picard.common.VLoggable
import io.violabs.picard.metaDsl.config.BuilderConfig

interface TestGenerator : VLoggable {
    fun generate(
        codeGenerator: CodeGenerator,
        domain: KSClassDeclaration,
        builderConfig: BuilderConfig,
        singleEntryTransformByClassName: Map<String, KSClassDeclaration>

    )
}