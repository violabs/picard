package io.violabs.picard.domain.builder

import io.violabs.picard.domain.PodTemplate
import io.violabs.picard.domain.Spec
import io.violabs.picard.scopedBuild

class PodTemplateBuilder : Builder<PodTemplate> {
    var spec: Spec? = null

    override fun build(): PodTemplate = PodTemplate(
        spec = spec
    )

    fun spec(scope: SpecBuilder.() -> Unit) {
        spec = scopedBuild(::SpecBuilder, scope)
    }
}