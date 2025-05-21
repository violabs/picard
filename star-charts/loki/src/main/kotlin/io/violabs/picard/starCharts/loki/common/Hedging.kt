package io.violabs.picard.starCharts.loki.common

import kotlin.time.Duration

data class Hedging(
    /**
     * # If set to a non-zero value a second request will be issued at the provided
     * # duration. Default is 0 (disabled)
     * # CLI flag: -common.storage.hedge-requests-at
     * [at: <duration> | default = 0s]
     */
    val at: Duration? = null,
    /**
     * # The maximum of hedge requests allowed.
     * # CLI flag: -common.storage.hedge-requests-up-to
     * [up_to: <int> | default = 2]
     */
    val upTo: Int? = null,
    /**
     * # The maximum of hedge requests allowed per seconds.
     * # CLI flag: -common.storage.hedge-max-per-second
     * [max_per_second: <int> | default = 5]
     */
    val maxPerSecond: Int? = null,
)