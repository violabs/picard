package io.violabs.picard.domain.k8sResources.authorization

data class K8sSubject(
    val kind: String,
    val name: String,
    val apiGroup: String? = null,
    val namespace: String? = null
)
