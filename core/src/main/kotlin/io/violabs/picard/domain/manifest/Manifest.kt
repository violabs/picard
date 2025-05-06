package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty

data class Manifest(
    val resources: List<ManifestResource>
) {

    class Builder : DSLBuilder<Manifest> {
        private val resources: MutableList<ManifestResource> = mutableListOf()

        fun authenticationSection(block: AuthenticationResourceSection.Builder.() -> Unit) {
            resources += AuthenticationResourceSection.Builder().apply(block).build()
        }

        fun authorizationSection(block: AuthorizationResourceSection.Builder.() -> Unit) {
            resources += AuthorizationResourceSection.Builder().apply(block).build()
        }

        fun clusterSection(block: ClusterResourceSection.Builder.() -> Unit) {
            resources += ClusterResourceSection.Builder().apply(block).build()
        }

        fun configSection(block: ConfigResourceSection.Builder.() -> Unit) {
            resources += ConfigResourceSection.Builder().apply(block).build()
        }

        fun extendSection(block: ExtendResourceSection.Builder.() -> Unit) {
            resources += ExtendResourceSection.Builder().apply(block).build()
        }

        fun policySection(block: PolicyResourceSection.Builder.() -> Unit) {
            resources += PolicyResourceSection.Builder().apply(block).build()
        }

        fun serviceSection(block: ServiceResourceSection.Builder.() -> Unit) {
            resources += ServiceResourceSection.Builder().apply(block).build()
        }

        fun storageSection(block: StorageResourceSection.Builder.() -> Unit) {
            resources += StorageResourceSection.Builder().apply(block).build()
        }

        fun workloadSection(block: WorkloadResourceSection.Builder.() -> Unit) {
            resources += WorkloadResourceSection.Builder().apply(block).build()
        }

        override fun build(): Manifest {
            return Manifest(
                resources = vRequireNotEmpty(this::resources)
            )
        }
    }
}