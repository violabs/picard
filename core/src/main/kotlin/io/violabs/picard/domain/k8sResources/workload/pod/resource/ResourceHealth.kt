package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.domain.DslBuilder

data class ResourceHealth(
    val resourceID: String,
    val health: Type? = null
) {
    enum class Type {
        Healthy,
        Unhealthy,
        Unknown
    }

    class Builder : DslBuilder<ResourceHealth> {
        var resourceID: String? = null
        var health: Type? = null

        override fun build(): ResourceHealth {
            return ResourceHealth(
                resourceID = requireNotNull(resourceID),
                health = health
            )
        }
    }
}