package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.resource.ResourceStatus
import io.violabs.picard.domain.k8sResources.workload.pod.resource.ResourceStatusGroup
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMountStatus

data class ContainerStatus(
    val imageID: String,
    val image: String,
    val name: String,
    val ready: Boolean,
    val allocatedResources: Map<String, Quantity>? = null,
    val allocatedResourceStatus: List<ResourceStatus>? = null,
    val containerID: String? = null,
    val lastState: ContainerState? = null,
    val resources: ContainerResourceRequirements? = null,
    val restartCount: Int? = null,
    val started: Boolean? = null,
    val state: ContainerState? = null,
    val user: ContainerUser? = null,
    val volumeMounts: VolumeMountStatus? = null
) {
    class Builder : DSLBuilder<ContainerStatus> {
        var imageID: String? = null
        var image: String? = null
        var name: String? = null
        private var ready: Boolean? = null
        private var allocatedResources: MutableMap<String, Quantity>? = null
        private var allocatedResourceStatus: MutableList<ResourceStatus>? = null
        var containerID: String? = null
        private var lastState: ContainerState? = null
        private var resources: ContainerResourceRequirements? = null
        var restartCount: Int? = null
        private var started: Boolean? = null
        private var state: ContainerState? = null
        private var user: ContainerUser? = null
        private var volumeMounts: VolumeMountStatus? = null

        fun ready(value: Boolean = true) {
            ready = value
        }

        fun allocatedResources(scope: MutableMap<String, Quantity>.() -> Unit) {
            this.allocatedResources = mutableMapOf<String, Quantity>().apply(scope)
        }

        fun allocatedResourceStatus(scope: ResourceStatusGroup.() -> Unit) {
            this.allocatedResourceStatus = ResourceStatusGroup().apply(scope).statuses()
        }

        fun lastState(scope: ContainerState.Builder.() -> Unit) {
            this.lastState = ContainerState.Builder().apply(scope).build()
        }

        fun resources(scope: ContainerResourceRequirements.Builder.() -> Unit) {
            this.resources = ContainerResourceRequirements.Builder().apply(scope).build()
        }

        fun started(value: Boolean = true) {
            started = value
        }

        fun state(scope: ContainerState.Builder.() -> Unit) {
            this.state = ContainerState.Builder().apply(scope).build()
        }

        fun user(scope: ContainerUser.Builder.() -> Unit) {
            this.user = ContainerUser.Builder().apply(scope).build()
        }

        fun volumeMounts(scope: VolumeMountStatus.Builder.() -> Unit) {
            this.volumeMounts = VolumeMountStatus.Builder().apply(scope).build()
        }

        override fun build(): ContainerStatus {
            return ContainerStatus(
                vRequireNotNull(this::imageID),
                vRequireNotNull(this::image),
                vRequireNotNull(this::name),
                vRequireNotNull(this::ready),
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