package io.violabs.picard.domain.builder

import io.violabs.picard.domain.KubeletConfiguration

class ApiVersionBuilder(
    var definedVersion: KubeletConfiguration.DefinedVersion? = null,
    val customVersion: String? = null
) : Builder<String> {
    override fun build(): String = definedVersion?.ref ?: customVersion ?: error("Please provide a version")
}