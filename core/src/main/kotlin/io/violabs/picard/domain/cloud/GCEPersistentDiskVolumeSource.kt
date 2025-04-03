package io.violabs.picard.domain.cloud

data class GCEPersistentDiskVolumeSource(
    val pdName: String,
    val fsType: String? = null,
    val partition: Int? = null,
    val readOnly: Boolean? = null
)