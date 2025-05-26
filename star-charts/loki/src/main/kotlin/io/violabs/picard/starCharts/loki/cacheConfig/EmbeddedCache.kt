package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class EmbeddedCache(
    /**
     *   # Whether embedded cache is enabled.
     *   # CLI flag: -<prefix>.embedded-cache.enabled
     *   [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    /**
     *   # Maximum memory size of the cache in MB.
     *   # CLI flag: -<prefix>.embedded-cache.max-size-mb
     *   [max_size_mb: <int> | default = 100]
     */
    val maxSizeMb: Int? = null,
    /**
     *   # Maximum number of entries in the cache.
     *   # CLI flag: -<prefix>.embedded-cache.max-size-items
     *   [max_size_items: <int> | default = 0]
     */
    val maxSizeItems: Int? = null,
    /**
     *   # The time to live for items in the cache before they get purged.
     *   # CLI flag: -<prefix>.embedded-cache.ttl
     *   [ttl: <duration> | default = 1h]
     */
    val ttl: Duration? = null,
)
