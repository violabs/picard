package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.domain.BaseSecurityContext
import io.violabs.picard.common.DSLBuilder

data class PodSecurityContext(
    val appArmorProfile: AppArmorProfile? = null,
    val fsGroup: Long? = null,
    val fsGroupChangePolicy: String? = null,
    val runAsUser: Long? = null,
    val runAsNonRoot: Boolean? = null,
    val runAsGroup: Long? = null,
    val seccompProfile: SeccompProfile? = null,
    val seLinuxOptions: SELinuxOptions? = null,
    val supplementalGroups: List<Long>? = null,
    val supplementalGroupsPolicy: String? = null,
    val sysctls: List<Sysctl>? = null,
    val windowsOptions: WindowsSecurityContextOptions? = null
) : BaseSecurityContext {
    class Builder : DSLBuilder<PodSecurityContext> {
        private var appArmorProfile: AppArmorProfile? = null
        var fsGroup: Long? = null
        var fsGroupChangePolicy: String? = null
        var runAsUser: Long? = null
        private var runAsNonRoot: Boolean? = null
        var runAsGroup: Long? = null
        private var seccompProfile: SeccompProfile? = null
        private var seLinuxOptions: SELinuxOptions? = null
        private var supplementalGroups: List<Long>? = null
        var supplementalGroupsPolicy: String? = null
        private var sysctls: List<Sysctl>? = null
        private var windowsOptions: WindowsSecurityContextOptions? = null

        fun appArmorProfile(scope: AppArmorProfile.Builder.() -> Unit) {
            appArmorProfile = AppArmorProfile.Builder().apply(scope).build()
        }

        fun runAsNonRoot(value: Boolean = true) {
            this.runAsNonRoot = value
        }

        fun seccompProfile(scope: SeccompProfile.Builder.() -> Unit) {
            seccompProfile = SeccompProfile.Builder().apply(scope).build()
        }

        fun seLinuxOptions(scope: SELinuxOptions.Builder.() -> Unit) {
            seLinuxOptions = SELinuxOptions.Builder().apply(scope).build()
        }

        fun supplementalGroups(vararg supplementalGroups: Long) {
            this.supplementalGroups = supplementalGroups.toList()
        }

        fun sysctls(scope: Sysctl.Group.() -> Unit) {
            sysctls = Sysctl.Group().apply(scope).sysctls()
        }

        fun windowsOptions(scope: WindowsSecurityContextOptions.Builder.() -> Unit) {
            windowsOptions = WindowsSecurityContextOptions.Builder().apply(scope).build()
        }

        override fun build(): PodSecurityContext {
            return PodSecurityContext(
                appArmorProfile = appArmorProfile,
                fsGroup = fsGroup,
                fsGroupChangePolicy = fsGroupChangePolicy,
                runAsUser = runAsUser,
                runAsNonRoot = runAsNonRoot,
                runAsGroup = runAsGroup,
                seccompProfile = seccompProfile,
                seLinuxOptions = seLinuxOptions,
                supplementalGroups = supplementalGroups,
                supplementalGroupsPolicy = supplementalGroupsPolicy,
                sysctls = sysctls,
                windowsOptions = windowsOptions
            )
        }
    }
}