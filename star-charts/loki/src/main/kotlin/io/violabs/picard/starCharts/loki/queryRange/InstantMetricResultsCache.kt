package io.violabs.picard.starCharts.loki.queryRange

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig

@GeneratedDsl
data class InstantMetricResultsCache(
    val cache: CacheConfig? = null,
    val compression: String? = null,
)