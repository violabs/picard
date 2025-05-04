package io.violabs.picard.domain.cloud

data class ISCSIPersistentVolumeSource(
    val iqn: String,
    val lun: String,
    val targetPortal: String,
    val chapAuthDiscovery: Boolean? = null,
    val chapAuthSession: Boolean? = null,
    val fsType: String? = null,
    val initiatorName: String? = null,
    val iscsiInterface: String? = null,
    val portals: List<String>? = null,
    val readOnly: Boolean? = null,
    val secretRef: SecretReference? = null
)