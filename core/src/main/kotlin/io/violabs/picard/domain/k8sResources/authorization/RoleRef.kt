package io.violabs.picard.domain.k8sResources.authorization

data class RoleRef(
    val apiGroup: String,
    val kind: String,
    val name: String
)
