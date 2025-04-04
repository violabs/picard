package io.violabs.picard.domain

data class DownwardAPIVolumeSource(
    val defaultMode: Int? = null,
    val items: List<DownwardAPIVolumeFile>? = null
)