package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class Memcached(
    /**
     *   # How long keys stay in the memcache.
     *   # CLI flag: -<prefix>.memcached.expiration
     *   [expiration: <duration> | default = 0s]
     */
    val expiration: Duration? = null,
    /**
     *   # How many keys to fetch in each batch.
     *   # CLI flag: -<prefix>.memcached.batchsize
     *   [batch_size: <int> | default = 4]
     */
    val batchSize: Int? = null,
    /**
     *   # Maximum active requests to memcache.
     *   # CLI flag: -<prefix>.memcached.parallelism
     *   [parallelism: <int> | default = 5]
     */
    val parallelism: Int? = null,
)
