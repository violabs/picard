package io.violabs.picard.domain.cloud

import io.violabs.picard.domain.SecretReference

data class ScaleIOPersistentVolumeSource(
    val gateway: String,
    val system: String,
    val secretRef: SecretReference,
    val fsType: String? = null,
    val protectionDomain: String? = null,
    val storagePool: String? = null,
    val storageMode: String? = null,
    val volumeName: String? = null,
    val readOnly: Boolean? = null,
    val sslEnabled: Boolean? = null
)