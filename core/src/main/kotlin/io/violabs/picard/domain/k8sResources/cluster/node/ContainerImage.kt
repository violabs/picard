package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.domain.DslBuilder

data class ContainerImage(
    val names: List<String>? = null,
    val sizeBytes: Long? = null
) {
    class Builder : DslBuilder<ContainerImage> {
        private var names: List<String>? = null
        var sizeBytes: Long? = null

        fun names(vararg names: String) {
            this.names = names.toList()
        }

        override fun build(): ContainerImage {
            return ContainerImage(
                names = names,
                sizeBytes = sizeBytes
            )
        }
    }
}