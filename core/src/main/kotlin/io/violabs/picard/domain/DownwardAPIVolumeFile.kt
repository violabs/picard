package io.violabs.picard.domain

data class DownwardAPIVolumeFile(
    val path: String,
    val fieldRef: ObjectFieldSelector? = null,
    val mode: Int? = null,
    val resourceFieldRef: ResourceFieldSelector? = null
)