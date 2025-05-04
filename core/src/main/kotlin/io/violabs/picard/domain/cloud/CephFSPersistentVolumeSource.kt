package io.violabs.picard.domain.cloud

data class CephFSPersistentVolumeSource(
    val monitors: List<String>,
    val path: String? = null,
    val readOnly: Boolean? = null,
    val secretFile: String? = null,
    val secretRef: SecretReference? = null,
    val user: String? = null
)