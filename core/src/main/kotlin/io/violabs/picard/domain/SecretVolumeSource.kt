package io.violabs.picard.domain

data class SecretVolumeSource(
    val secretName: String? = null,
    val secretOptional: Boolean? = null,
    val defaultMode: Int? = null,
    val items: List<KeyToPath>? = null
)