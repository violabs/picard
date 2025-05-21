package io.violabs.picard.starCharts.loki.common.congestionControl

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class CongestionControl(
    /**
     * # Use storage congestion control (default: disabled).
     * # CLI flag: -common.storage.congestion-control.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    val controller: CongestionController? = null,
    val retry: CongestionControlRetry? = null,
    val hedging: HedgingWrapper? = null
)