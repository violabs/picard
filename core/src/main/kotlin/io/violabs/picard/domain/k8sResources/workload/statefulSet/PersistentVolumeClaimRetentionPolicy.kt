package io.violabs.picard.domain.k8sResources.workload.statefulSet

import io.violabs.picard.common.DSLBuilder

data class PersistentVolumeClaimRetentionPolicy(
    val whenDeleted: String? = null,
    val whenScaled: String? = null
) {
    class Builder : DSLBuilder<PersistentVolumeClaimRetentionPolicy> {
        var whenDeleted: String? = null
        var whenScaled: String? = null

        override fun build(): PersistentVolumeClaimRetentionPolicy {
            return PersistentVolumeClaimRetentionPolicy(
                whenDeleted = whenDeleted,
                whenScaled = whenScaled
            )
        }
    }
}