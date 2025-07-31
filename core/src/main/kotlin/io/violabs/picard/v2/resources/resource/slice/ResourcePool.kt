package io.violabs.picard.v2.resources.resource.slice

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ResourcePool(
    val generation: Long,
    val name: String,
    val resourceSliceCount: Long
)