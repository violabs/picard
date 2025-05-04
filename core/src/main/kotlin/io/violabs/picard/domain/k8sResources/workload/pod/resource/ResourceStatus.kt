package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class ResourceStatus(
    val name: String,
    val image: String,
    val imageID: String,
    val resources: List<ResourceHealth>? = null,
    val containerID: String? = null
) {
    class Builder : DSLBuilder<ResourceStatus> {
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
                name = vRequireNotNull(this::name),
                image = vRequireNotNull(this::image),
                imageID = vRequireNotNull(this::imageID),
                resources = resources,
                containerID = containerID
            )
        }
    }
}
