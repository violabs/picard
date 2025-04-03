package io.violabs.picard.domain.cloud

import io.violabs.picard.domain.SecretReference

data class CSIPersistentVolumeSource(
    val driver: String,
    val volumeHandle: String,
    val controllerExpandSecretRef: SecretReference? = null,
    val controllerPublishSecretRef: SecretReference? = null,
    val fsType: String? = null,
    val nodeExpandSecretRef: SecretReference? = null,
    val nodePublishSecretRef: SecretReference? = null,
    val nodeStageSecretRef: SecretReference? = null,
    val readOnly: Boolean? = null,
    val volumeAttributes: Map<String, String>? = null
)