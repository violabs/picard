package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder

data class ContainerImage(
    val names: List<String>? = null,
    val sizeBytes: Long? = null
) {
    class Builder : DslBuilder<ContainerImage> {
        var names: List<String>? = null
        var sizeBytes: Long? = null
        override fun build(): ContainerImage {
            return ContainerImage(
                names = names,
                sizeBytes = sizeBytes
            )
        }
    }
}