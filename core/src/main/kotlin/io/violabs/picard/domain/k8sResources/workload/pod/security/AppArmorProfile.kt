package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class AppArmorProfile(
    val type: SecurityProfileType,
    val localHostProfile: String? = null
) {
    class Builder : DSLBuilder<AppArmorProfile> {
        var type: SecurityProfileType? = null
        var localHostProfile: String? = null

        override fun build(): AppArmorProfile {
            return AppArmorProfile(
                type = vRequireNotNull(this::type),
                localHostProfile = localHostProfile
            )
        }
    }
}