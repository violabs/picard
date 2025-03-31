package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Manifest
import io.violabs.picard.domain.PodResource

class ManifestBuilder : Builder<Manifest> {
    private val resources: MutableList<PodResource> = mutableListOf()

    override fun build(): Manifest {
        return Manifest(resources)
    }

    fun resource(scope: PodResourceBuilder.() -> Unit) {
        resources.add(scopedBuild(::PodResourceBuilder, scope))
    }
}