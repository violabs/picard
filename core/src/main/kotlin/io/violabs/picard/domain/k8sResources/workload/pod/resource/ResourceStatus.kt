package io.violabs.picard.domain.k8sResources.workload.pod.resource

data class ResourceStatus(
    val name: String,
    val image: String,
    val imageID: String,
    val resources: List<ResourceHealth>? = null,
    val containerID: String? = null
)
