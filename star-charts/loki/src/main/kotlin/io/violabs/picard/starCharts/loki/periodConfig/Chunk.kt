package io.violabs.picard.starCharts.loki.periodConfig

import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.dsl.annotation.GeneratedDsl

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