package io.violabs.picard.domain.cloud

data class QuobyteVolumeSource(
    val registry: String,
    val volume: String,
    val user: String? = null,
    val group: String? = null,
    val readOnly: Boolean? = null,
    val tenant: String? = null
)