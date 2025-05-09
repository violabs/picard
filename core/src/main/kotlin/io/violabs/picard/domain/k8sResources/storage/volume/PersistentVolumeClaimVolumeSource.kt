package io.violabs.picard.domain.k8sResources.storage.volume

import io.violabs.picard.common.DSLBuilder

class PersistentVolumeClaimVolumeSource(
    val claimName: String,
    val readOnly: Boolean? = null
) {

    class Builder : DSLBuilder<PersistentVolumeClaimVolumeSource> {
        var claimName: String? = null
        private var readOnly: Boolean? = null

        fun readOnly(value: Boolean = true) {
            readOnly = value
        }

        override fun build(): PersistentVolumeClaimVolumeSource {
            return PersistentVolumeClaimVolumeSource(
                claimName = requireNotNull(claimName),
                readOnly = readOnly
            )
        }
    }
}