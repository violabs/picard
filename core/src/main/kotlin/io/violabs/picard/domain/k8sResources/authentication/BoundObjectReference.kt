package io.violabs.picard.domain.k8sResources.authentication

data class BoundObjectReference(
    val apiVersion: String? = null,
    val kind: String? = null,
    val name: String? = null,
    val uid: String? = null
)
