package io.violabs.picard.domain.k8sResources.pod.security

data class SeccompProfile(
    val type: SecurityProfileType,
    val localhostProfile: String? = null,
)