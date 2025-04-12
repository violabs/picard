package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.domain.DslBuilder

data class ResourceStatus(
    val name: String,
    val image: String,
    val imageID: String,
    val resources: List<ResourceHealth>? = null,
    val containerID: String? = null
) {
    class Builder : DslBuilder<ResourceStatus> {
        var name: String? = null
        var image: String? = null
        var imageID: String? = null
        private var resources: List<ResourceHealth>? = null
        var containerID: String? = null

        fun resources(scope: ResourceHealthGroup.() -> Unit) {
            resources = ResourceHealthGroup().apply(scope).resources()
        }

        override fun build(): ResourceStatus {
            return ResourceStatus(
                name = requireNotNull(name),
                image = requireNotNull(image),
                imageID = requireNotNull(imageID),
                resources = resources,
                containerID = containerID
            )
        }
    }
}
