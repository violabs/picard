package io.violabs.picard.domain.k8sResources.pod.security

data class WindowsSecurityContextOptions(
    val gmsaCredentialSpec: String? = null,
    val gmsaCredentialSpecName: String? = null,
    val hostProcess: Boolean? = null,
    val runAsUserName: String? = null
)