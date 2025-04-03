package io.violabs.picard.domain.cloud

data class AzureFilePersistentVolumeSource(
    val secretName: String,
    val shareName: String,
    val readOnly: Boolean? = null,
    val secretNamespace: String? = null
)