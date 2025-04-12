package io.violabs.picard.domain.k8sResources.workload.pod.resource

data class ResourceHealth(
    val resourceID: String,
    val health: Type? = null
) {
    enum class Type {
        Healthy,
        Unhealthy,
        Unknown
    }
}