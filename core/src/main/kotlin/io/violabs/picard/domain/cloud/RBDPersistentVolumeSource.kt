package io.violabs.picard.domain.cloud

import io.violabs.picard.domain.SecretReference

data class RBDPersistentVolumeSource(
    val image: String,
    val monitors: List<String>,
    val fsType: String? = null,
    val pool: String? = null,
    val user: String? = null,
    val keyring: String? = null,
    val secretRef: SecretReference? = null,
    val readOnly: Boolean? = null
)