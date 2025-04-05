package io.violabs.picard.domain.k8sResources.pod.security

data class AppArmorProfile(
    val type: SecurityProfileType,
    val localHostProfile: String? = null
)