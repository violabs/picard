package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty

data class Manifest(
    val resources: List<ManifestResource>
) {

    class Builder : DSLBuilder<Manifest> {
        private val resources: MutableList<ManifestResource> = mutableListOf()

        fun authentication(block: AuthenticationResourceSection.Builder.() -> Unit) {
            val section = AuthenticationResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        override fun build(): Manifest {
            return Manifest(
                resources = vRequireNotEmpty(this::resources)
            )
        }
    }
}