package io.violabs.picard.domain.cloud

import io.violabs.picard.domain.SecretReference

data class CinderPersistentVolumeSource(
    val volumeID: String,
    val fsType: String? = null,
    val readOnly: Boolean? = null,
    val secretRef: SecretReference? = null
)