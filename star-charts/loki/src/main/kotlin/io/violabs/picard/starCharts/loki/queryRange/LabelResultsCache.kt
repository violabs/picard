package io.violabs.picard.starCharts.loki.queryRange

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.cacheConfig.CacheConfig

@GeneratedDSL
data class LabelResultsCache(
    val cache: CacheConfig? = null,
    val compression: String? = null,
)