package io.violabs.picard.domain.k8sResources.workload.pod.resource


import io.violabs.picard.common.BuilderGroup

class ResourceHealthGroup : BuilderGroup<ResourceHealth, ResourceHealth.Builder>(ResourceHealth.Builder()) {
    fun resources(): List<ResourceHealth>? = items()

    fun resource(scope: ResourceHealth.Builder.() -> Unit) {
        add(scope)
    }
}