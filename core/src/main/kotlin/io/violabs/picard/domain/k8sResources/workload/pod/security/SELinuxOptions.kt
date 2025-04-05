package io.violabs.picard.domain.k8sResources.workload.pod.security

data class SELinuxOptions(
    val level: String? = null,
    val role: String? = null,
    val type: String? = null,
    val user: String? = null
)