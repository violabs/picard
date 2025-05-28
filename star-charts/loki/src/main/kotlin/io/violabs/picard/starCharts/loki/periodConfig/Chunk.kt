package io.violabs.picard.starCharts.loki.periodConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Quantity

@GeneratedDsl
data class Chunk(
    /**
     * Table prefix for all period tables.
     */
    val prefix: String? = null,
    /**
     * Table period.
     */
    val period: Quantity? = null,
    /**
     * A map to be added to all managed tables.
     */
    val tags: Map<String, String>? = null
)