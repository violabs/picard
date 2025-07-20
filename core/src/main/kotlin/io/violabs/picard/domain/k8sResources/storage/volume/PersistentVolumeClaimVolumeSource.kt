package io.violabs.picard.domain.k8sResources.storage.volume

import io.violabs.picard.common.DslBuilder

class PersistentVolumeClaimVolumeSource(
    val claimName: String,
    val readOnly: Boolean? = null
) {

    class Builder : DslBuilder<PersistentVolumeClaimVolumeSource> {
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