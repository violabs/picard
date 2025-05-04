package io.violabs.picard.domain.cloud

data class CinderPersistentVolumeSource(
    val volumeID: String,
    val fsType: String? = null,
    val readOnly: Boolean? = null,
    val secretRef: SecretReference? = null
)