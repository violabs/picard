package io.violabs.picard.starCharts.loki.minioConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(
    withListGroup = true
)
data class MinioBucket(
    val name: String? = null,
    /**
     * default is "none"
     */
    val policy: String? = null,
    /**
     * default is false
     */
    val purge: Boolean? = null
)