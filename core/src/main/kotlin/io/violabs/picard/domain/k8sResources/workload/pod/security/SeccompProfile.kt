package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class SeccompProfile(
    val type: SecurityProfileType,
    val localhostProfile: String? = null,
) {
    class Builder : DSLBuilder<SeccompProfile> {
        var type: SecurityProfileType? = null
        var localhostProfile: String? = null

        override fun build(): SeccompProfile  {
            return SeccompProfile(
                type = vRequireNotNull(this::type),
                localhostProfile = localhostProfile
            )
        }
    }
}