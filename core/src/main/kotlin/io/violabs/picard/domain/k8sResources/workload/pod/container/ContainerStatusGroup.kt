package io.violabs.picard.domain.k8sResources.workload.pod.container


import io.violabs.picard.common.BuilderGroup

class ContainerStatusGroup : BuilderGroup<ContainerStatus, ContainerStatus.Builder>(ContainerStatus.Builder()) {
    fun statuses(): MutableList<ContainerStatus>? = items()

    fun containerStatus(scope: ContainerStatus.Builder.() -> Unit) {
        add(scope)
    }
}