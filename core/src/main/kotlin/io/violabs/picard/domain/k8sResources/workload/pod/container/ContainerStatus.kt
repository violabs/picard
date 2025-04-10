package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.Resource
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount

data class ContainerStatus(
    val imageID: String,
    val image: String,
    val name: String,
    val ready: Boolean,
    val allocatedResources: Map<String, Quantity>? = null,
    val allocatedResourceStatus: List<Resource.Status>? = null,
    val containerID: String? = null,
    val lastState: ContainerState? = null,
    val resources: ContainerResourceRequirements? = null,
    val restartCount: Int? = null,
    val started: Boolean? = null,
    val state: ContainerState? = null,
    val user: ContainerUser? = null,
    val volumeMounts: VolumeMount.Status? = null
) {
    class Builder : DslBuilder<ContainerStatus> {
        var imageID: String? = null
        var image: String? = null
        var name: String? = null
        var ready: Boolean? = null
        var allocatedResources: Map<String, Quantity>? = null
        var allocatedResourceStatus: List<Resource.Status>? = null
        var containerID: String? = null
        var lastState: ContainerState? = null
        var resources: ContainerResourceRequirements? = null
        var restartCount: Int? = null
        var started: Boolean? = null
        var state: ContainerState? = null
        var user: ContainerUser? = null
        var volumeMounts: VolumeMount.Status? = null

        override fun build(): ContainerStatus {
            return ContainerStatus(
                requireNotNull(imageID) { "image ID must not be null" },
                requireNotNull(image) { "image must not be null" },
                requireNotNull(name) { "name must not be null" },
                requireNotNull(ready) { "ready must not be null" },
                allocatedResources,
                allocatedResourceStatus,
                containerID,
                lastState,
                resources,
                restartCount,
                started,
                state,
                user,
                volumeMounts
            )
        }
    }
}