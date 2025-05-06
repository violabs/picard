package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.common.BuilderGroup
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

        fun resources(scope: ResourceHealth.Group.() -> Unit) {
            resources = ResourceHealth.Group().apply(scope).resourceHealths()
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

    class Group : BuilderGroup<ResourceStatus, Builder>(Builder()) {
        fun statuses(): List<ResourceStatus>? = items()

        fun addResourceStatus(block: Builder.() -> Unit) {
            add(block)
        }
    }
}
