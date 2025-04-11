package io.violabs.picard.domain.k8sResources.workload.pod

object Resource {
    data class Status(
        val name: String,
        val image: String,
        val imageID: String,
        val resources: List<Health>? = null,
        val containerID: String? = null
    )

    data class Health(
        val resourceID: String,
        val health: Type? = null
    ) {
        enum class Type {
            Healthy,
            Unhealthy,
            Unknown
        }
    }
}
