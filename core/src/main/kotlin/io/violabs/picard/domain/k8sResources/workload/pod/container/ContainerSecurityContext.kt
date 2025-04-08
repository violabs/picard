package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.security.AppArmorProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.SELinuxOptions
import io.violabs.picard.domain.k8sResources.workload.pod.security.SeccompProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.WindowsSecurityContextOptions

data class SecurityContext(
    val allowPrivilegeEscalation: Boolean? = null,
    val appArmorProfile: AppArmorProfile? = null,
    val capabilities: Capabilities? = null,
    val procMount: String? = null,
    val privileged: Boolean? = null,
    val readOnlyRootFilesystem: Boolean? = null,
    val runAsUser: Long? = null,
    val runAsNonRoot: Boolean? = null,
    val runAsGroup: Long? = null,
    val seLinuxOptions: SELinuxOptions? = null,
    val seccompProfile: SeccompProfile? = null,
    val windowsOptions: WindowsSecurityContextOptions? = null
) {
    data class Capabilities(
        val add: List<String>? = null,
        val drop: List<String>? = null
    ) {
        class Builder : DslBuilder<Capabilities> {
            private var add: List<String>? = null
            private var drop: List<String>? = null
            override fun build(): Capabilities {
                return Capabilities(
                    add = add,
                    drop = drop
                )
            }
        }
    }

    class Builder : DslBuilder<SecurityContext> {
        private var allowPrivilegeEscalation: Boolean? = null
        private var appArmorProfile: AppArmorProfile? = null
        private var capabilities: Capabilities? = null
        private var procMount: String? = null
        private var privileged: Boolean? = null
        private var readOnlyRootFilesystem: Boolean? = null
        private var runAsUser: Long? = null
        private var runAsNonRoot: Boolean? = null
        private var runAsGroup: Long? = null
        private var seLinuxOptions: SELinuxOptions? = null
        private var seccompProfile: SeccompProfile? = null
        private var windowsOptions: WindowsSecurityContextOptions? = null

        override fun build(): SecurityContext {
            return SecurityContext(
                allowPrivilegeEscalation = allowPrivilegeEscalation,
                appArmorProfile = appArmorProfile,
                capabilities = capabilities,
                procMount = procMount,
                privileged = privileged,
                readOnlyRootFilesystem = readOnlyRootFilesystem,
                runAsUser = runAsUser,
                runAsNonRoot = runAsNonRoot,
                runAsGroup = runAsGroup,
                seLinuxOptions = seLinuxOptions,
                seccompProfile = seccompProfile,
                windowsOptions = windowsOptions
            )
        }
    }
}