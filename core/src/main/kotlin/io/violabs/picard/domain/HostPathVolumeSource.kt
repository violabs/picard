package io.violabs.picard.domain

data class HostPathVolumeSource(
    val path: String,
    val type: String? = null
)