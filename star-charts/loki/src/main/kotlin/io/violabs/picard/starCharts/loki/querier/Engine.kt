package io.violabs.picard.starCharts.loki.querier

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Quantity

@GeneratedDsl
data class Engine(
    /**
     * The maximum amount of time to look back for log lines. Used only for instant
     * log queries.
     * CLI flag: -querier.engine.max-lookback-period
     */
    val maxLookBackPeriod: Quantity? = null,
    /**
     * The maximum number of labels the heap of a topk query using a count min
     * sketch can track.
     * CLI flag: -querier.engine.max-count-min-sketch-heap-size
     */
    val maxCountMinSketchHeapSize: Int? = null
)