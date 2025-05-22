package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class CacheConfig(
    /**
     * # The default validity of entries for caches unless overridden.
     * # CLI flag: -<prefix>.default-validity
     * [default_validity: <duration> | default = 1h]
     */
    val defaultValidity: Duration? = null,
    val background: Background? = null,
    val memcached: Memcached? = null,
    val memcachedClient: MemcachedClient? = null,
    val redis: Redis? = null,
    val embeddedCache: EmbeddedCache? = null,
)