package io.violabs.picard.domain.cloud

class FCVolumeSource(
    val fsType: String? = null,
    val lun: Int? = null,
    val readOnly: Boolean? = null,
    val targetWWNs: List<String>? = null,
    val wwids: List<String>? = null
)