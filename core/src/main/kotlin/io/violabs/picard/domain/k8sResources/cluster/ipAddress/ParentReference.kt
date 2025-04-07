package io.violabs.picard.domain.k8sResources.cluster.ipAddress

data class ParentReference(
    val name: String,
    val resource: String,
    val group: String? = null,
    val namespace: String? = null
)