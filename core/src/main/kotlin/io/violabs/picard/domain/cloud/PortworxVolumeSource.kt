package io.violabs.picard.domain.cloud

class PortworxVolumeSource(
    val volumeID: String,
    val fsType: String? = null,
    val readOnly: Boolean? = null
)