package io.violabs.picard.starCharts.loki.queryRange

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig

@GeneratedDsl
data class IndexStatsResultsCache(
    val cache: CacheConfig? = null,
    val compression: String? = null,
)