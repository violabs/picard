package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class CacheConfig(
    /**
     * # The default validity of entries for caches unless overridden.
     * # CLI flag: -<prefix>.default-validity
     * [default_validity: <duration> | default = 1h]
     */
    val defaultValidity: Duration? = null,
    val background: cacheConfig.Background? = null,
    val memcached: cacheConfig.Memcached? = null,
    val memcachedClient: cacheConfig.MemcachedClient? = null,
    val redis: cacheConfig.Redis? = null,
    val embeddedCache: cacheConfig.EmbeddedCache? = null,
)
