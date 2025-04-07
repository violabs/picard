package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.K8sEnum

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
        enum class Type : K8sEnum {
            HEALTHY,
            UNHEALTHY,
            UNKNOWN;

            override fun toString(): String = properCase()
        }
    }
}
