package io.violabs.picard.domain.cloud

data class AzureDiskVolumeSource(
    val diskName: String,
    val diskURI: String,
    val cachingMode: String? = null,
    val fsType: String? = null,
    val kind: String? = null,
    val readOnly: Boolean? = null
)