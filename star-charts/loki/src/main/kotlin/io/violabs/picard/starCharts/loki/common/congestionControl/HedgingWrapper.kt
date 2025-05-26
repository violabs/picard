package io.violabs.picard.starCharts.loki.common.congestionControl

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.common.Hedging

@GeneratedDsl
data class HedgingWrapper(
    val config: Hedging? = null,
    /**
     * # Congestion control hedge strategy to use (default: none, options:
     * # 'limited').
     * # CLI flag: -common.storage.congestion-control.hedge.strategy
     * [strategy: <string> | default = ""]
     */
    val strategy: String? = null
)