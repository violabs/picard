package io.violabs.picard.dslTest

import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.violabs.picard.metaDsl.config.BuilderConfig
import io.violabs.picard.metaDsl.config.DomainConfig

class TestDomainConfig(
    builderConfig: BuilderConfig,
    singleEntryTransformByClassName: Map<String, KSClassDeclaration>,
    domain: KSClassDeclaration,
) : DomainConfig(
    builderConfig,
    singleEntryTransformByClassName,
    domain
) {
    val testDslBuildFilePostfix: String = "DslBuilderTest"
}