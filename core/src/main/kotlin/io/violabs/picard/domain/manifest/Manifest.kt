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

        fun authorization(block: AuthorizationResourceSection.Builder.() -> Unit) {
            val section = AuthorizationResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        fun cluster(block: ClusterResourceSection.Builder.() -> Unit) {
            val section = ClusterResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        fun config(block: ConfigResourceSection.Builder.() -> Unit) {
            val section = ConfigResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        fun extend(block: ExtendResourceSection.Builder.() -> Unit) {
            val section = ExtendResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        fun policy(block: PolicyResourceSection.Builder.() -> Unit) {
            val section = PolicyResourceSection.Builder().apply(block).build()
            resources.add(section)
        }

        override fun build(): Manifest {
            return Manifest(
                resources = vRequireNotEmpty(this::resources)
            )
        }
    }
}