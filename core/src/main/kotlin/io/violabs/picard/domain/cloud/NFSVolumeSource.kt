package io.violabs.picard.domain.cloud

data class NFSVolumeSource(
    val path: String,
    val server: String,
    val readOnly: Boolean? = null
)