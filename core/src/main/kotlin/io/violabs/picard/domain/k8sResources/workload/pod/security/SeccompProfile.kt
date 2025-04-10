package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.domain.DslBuilder

data class SeccompProfile(
    val type: SecurityProfileType,
    val localhostProfile: String? = null,
) {
    class Builder : DslBuilder<SeccompProfile> {
        var type: SecurityProfileType? = null
        var localhostProfile: String? = null

        override fun build(): SeccompProfile  {
            return SeccompProfile(
                type = requireNotNull(type) { "type cannot be null" },
                localhostProfile = localhostProfile
            )
        }
    }
}