package io.violabs.picard.domain.cloud

data class PhotonPersistentDisk(
    val pdId: String,
    val fsType: String? = null
)