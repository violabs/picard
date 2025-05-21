package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class Background(
    /**
     *   # At what concurrency to write back to cache.
     *   # CLI flag: -<prefix>.background.write-back-concurrency
     *   [writeback_goroutines: <int> | default = 1]
     */
    val writebackGoroutines: Int? = null,
    /**
     *   # How many key batches to buffer for background write-back. Default is large
     *   # to prefer size based limiting.
     *   # CLI flag: -<prefix>.background.write-back-buffer
     *   [writeback_buffer: <int> | default = 500000]
     */
    val writebackBuffer: Int? = null,
    /**
     *   # Size limit in bytes for background write-back.
     *   # CLI flag: -<prefix>.background.write-back-size-limit
     *   [writeback_size_limit: <int> | default = 500MB]
     */
    val writebackSizeLimit: Int? = null,
)
