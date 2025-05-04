package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty

data class Manifest(
    val resources: List<ManifestResource>
) {

    class Builder : DSLBuilder<Manifest> {
        private val resources: MutableList<ManifestResource> = mutableListOf()

        fun authentication(block: AuthenticationResourceSection.Builder.() -> Unit) {
            resources += AuthenticationResourceSection.Builder().apply(block).build()
        }

        fun authorization(block: AuthorizationResourceSection.Builder.() -> Unit) {
            resources += AuthorizationResourceSection.Builder().apply(block).build()
        }

        fun cluster(block: ClusterResourceSection.Builder.() -> Unit) {
            resources += ClusterResourceSection.Builder().apply(block).build()
        }

        fun config(block: ConfigResourceSection.Builder.() -> Unit) {
            resources += ConfigResourceSection.Builder().apply(block).build()
        }

        fun extend(block: ExtendResourceSection.Builder.() -> Unit) {
            resources += ExtendResourceSection.Builder().apply(block).build()
        }

        fun policy(block: PolicyResourceSection.Builder.() -> Unit) {
            resources += PolicyResourceSection.Builder().apply(block).build()
        }

        fun service(block: ServiceResourceSection.Builder.() -> Unit) {
            resources += ServiceResourceSection.Builder().apply(block).build()
        }

        override fun build(): Manifest {
            return Manifest(
                resources = vRequireNotEmpty(this::resources)
            )
        }
    }
}