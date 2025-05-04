package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.security.AppArmorProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.SELinuxOptions
import io.violabs.picard.domain.k8sResources.workload.pod.security.SeccompProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.WindowsSecurityContextOptions

data class ContainerSecurityContext(
    val allowPrivilegeEscalation: Boolean? = null,
    val appArmorProfile: AppArmorProfile? = null,
    val capabilities: Capabilities? = null,
    val procMount: String? = null,
    val privileged: Boolean? = null,
    val readOnlyRootFilesystem: Boolean? = null,
    val runAsUser: Long? = null,
    val runAsGroup: Long? = null,
    val runAsNonRoot: Boolean? = null,
    val seLinuxOptions: SELinuxOptions? = null,
    val seccompProfile: SeccompProfile? = null,
    val windowsOptions: WindowsSecurityContextOptions? = null
) {
    data class Capabilities(
        val add: List<String>? = null,
        val drop: List<String>? = null
    ) {
        class Builder : DSLBuilder<Capabilities> {
            private var add: List<String>? = null
            private var drop: List<String>? = null

            fun add(vararg add: String) {
                this.add = add.toList()
            }

            fun drop(vararg drop: String) {
                this.drop = drop.toList()
            }

            override fun build(): Capabilities {
                return Capabilities(
                    add = add,
                    drop = drop
                )
            }
        }
    }

    class Builder : DSLBuilder<ContainerSecurityContext> {
        var allowPrivilegeEscalation: Boolean? = null
        private var appArmorProfile: AppArmorProfile? = null
        private var capabilities: Capabilities? = null
        var procMount: String? = null
        var privileged: Boolean? = null
        var readOnlyRootFilesystem: Boolean? = null
        var runAsUser: Long? = null
        var runAsNonRoot: Boolean? = null
        var runAsGroup: Long? = null
        private var seLinuxOptions: SELinuxOptions? = null
        private var seccompProfile: SeccompProfile? = null
        private var windowsOptions: WindowsSecurityContextOptions? = null

        fun allowPrivilegeEscalation() {
            this.allowPrivilegeEscalation = true
        }

        fun appArmorProfile(scope: AppArmorProfile.Builder.() -> Unit) {
            appArmorProfile = AppArmorProfile.Builder().apply(scope).build()
        }

        fun capabilities(scope: Capabilities.Builder.() -> Unit) {
            capabilities = Capabilities.Builder().apply(scope).build()
        }

        fun priveleged() {
            this.privileged = true
        }

        fun readOnlyRootFilesystem() {
            this.readOnlyRootFilesystem = true
        }

        fun runAsNonRoot() {
            this.runAsNonRoot = true
        }

        fun seLinuxOptions(scope: SELinuxOptions.Builder.() -> Unit) {
            seLinuxOptions = SELinuxOptions.Builder().apply(scope).build()
        }

        fun seccompProfile(scope: SeccompProfile.Builder.() -> Unit) {
            seccompProfile = SeccompProfile.Builder().apply(scope).build()
        }

        fun windowsOptions(scope: WindowsSecurityContextOptions.Builder.() -> Unit) {
            windowsOptions = WindowsSecurityContextOptions.Builder().apply(scope).build()
        }

        override fun build(): ContainerSecurityContext {
            return ContainerSecurityContext(
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