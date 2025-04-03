package io.violabs.picard.domain.cloud

data class AWSElasticBlockStoreVolumeSource(
    val volumeID: String,
    val fsType: String? = null,
    val partition: Int? = null,
    val readOnly: Boolean? = null
)