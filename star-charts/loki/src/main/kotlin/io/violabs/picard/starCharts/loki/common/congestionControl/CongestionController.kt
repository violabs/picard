package io.violabs.picard.starCharts.loki.common.congestionControl

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class CongestionController(
    /**
     * Congestion control strategy to use (default: none, options: 'aimd').
     * # CLI flag: -common.storage.congestion-control.strategy
     * [strategy: <string> | default = ""]
     */
    val strategy: String? = null,
    val aimd: AIMD? = null,
)