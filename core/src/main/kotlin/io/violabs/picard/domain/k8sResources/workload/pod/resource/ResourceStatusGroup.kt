package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.domain.BuilderGroup

class ResourceStatusGroup : BuilderGroup<ResourceStatus, ResourceStatus.Builder>(ResourceStatus.Builder()) {
    fun statuses(): MutableList<ResourceStatus>? = items()

    fun resourceStatus(scope: ResourceStatus.Builder.() -> Unit) {
        add(scope)
    }
}