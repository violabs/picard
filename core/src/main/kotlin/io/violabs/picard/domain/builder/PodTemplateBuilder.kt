package io.violabs.picard.domain.builder

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.PodTemplate
import io.violabs.picard.domain.Spec

class PodTemplateBuilder : Builder<PodTemplate>, DefaultLogger(PodTemplateBuilder::class) {
    var spec: Spec? = null
    var metadata: ObjectMetadata? = null

    override fun build(): PodTemplate = PodTemplate(
        spec = requireNotNull(spec) { "Please provide a spec" },
        metadata = metadata
    )

    fun spec(scope: SpecBuilder.() -> Unit) {
        spec = scopedBuild(::SpecBuilder, scope)
    }

    fun metadata(scope: MetadataBuilder.() -> Unit) {
        metadata = scopedBuild(::MetadataBuilder, scope)
        log.debug("template metadata: $metadata")
    }
}