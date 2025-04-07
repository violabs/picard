package io.violabs.picard.domain.k8sResources.authentication

data class UserInfo(
    val extra: Map<String, List<String>>? = null,
    val groups: List<String>? = null,
    val uid: String? = null,
    val username: String? = null
)
