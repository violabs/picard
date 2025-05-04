package io.violabs.picard.domain.cloud

data class FlexPersistentVolumeSource(
    val driver: String,
    val fsType: String? = null,
    val options: Map<String, String>? = null,
    val readOnly: Boolean? = null,
    val secretRef: SecretReference? = null
)