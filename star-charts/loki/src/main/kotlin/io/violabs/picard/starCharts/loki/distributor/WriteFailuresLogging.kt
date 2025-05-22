package io.violabs.picard.starCharts.loki.distributor

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class WriteFailuresLogging(
    /**
     *   # Log volume allowed (per second). Default: 1KB.
     *   # CLI flag: -distributor.write-failures-logging.rate
     *   [rate: <int> | default = 1KB]
     */
    val rate: Int? = null,
    /**
     *   # Whether a insight=true key should be logged or not. Default: false.
     *   # CLI flag: -distributor.write-failures-logging.add-insights-label
     *   [add_insights_label: <boolean> | default = false]
     */
    val addInsightsLabel: Boolean? = null
)