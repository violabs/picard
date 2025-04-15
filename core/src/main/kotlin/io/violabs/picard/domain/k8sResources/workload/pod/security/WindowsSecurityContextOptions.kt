package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.domain.DSLBuilder

data class WindowsSecurityContextOptions(
    val gmsaCredentialSpec: String? = null,
    val gmsaCredentialSpecName: String? = null,
    val hostProcess: Boolean? = null,
    val runAsUserName: String? = null
) {
    class Builder() : DSLBuilder<WindowsSecurityContextOptions> {
        var gmsaCredentialSpec: String? = null
        var gmsaCredentialSpecName: String? = null
        var hostProcess: Boolean? = null
        var runAsUserName: String? = null

        fun hostProcess() {
            hostProcess = true
        }

        override fun build(): WindowsSecurityContextOptions  {
            return WindowsSecurityContextOptions(
                gmsaCredentialSpec = gmsaCredentialSpec,
                gmsaCredentialSpecName = gmsaCredentialSpecName,
                hostProcess = hostProcess,
                runAsUserName = runAsUserName
            )
        }
    }
}