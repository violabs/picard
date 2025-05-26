package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Tracing(
    /**
     * # Set to false to disable tracing.
     * # CLI flag: -tracing.enabled
     * [enabled: <boolean> | default = true]
     */
    val enabled: Boolean? = null
)
