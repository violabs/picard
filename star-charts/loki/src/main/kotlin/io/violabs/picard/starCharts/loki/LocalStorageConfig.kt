package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL(
    withMapGroup = "SINGLE"
)
data class LocalStorageConfig(
    /**
     * # Directory to store chunks in.
     * # CLI flag: -local.chunk-directory
     * [directory: <string> | default = ""]
     */
    val directory: String? = null,
)
