package io.violabs.picard.domain.cloud

data class VsphereVirtualDiskVolumeSource(
    val volumePath: String,
    val fsType: String? = null,
    val storagePolicyName: String? = null,
    val storagePolicyID: String? = null
)