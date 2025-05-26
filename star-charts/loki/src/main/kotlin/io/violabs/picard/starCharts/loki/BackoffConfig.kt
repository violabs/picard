package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class BackoffConfig(
    /**
     *   # Minimum delay when backing off.
     *   # CLI flag: -<prefix>.backoff-min-period
     *   [min_period: <duration> | default = 100ms]
     */
    val minPeriod: Duration? = null,
    /**
     *   # Maximum delay when backing off.
     *   # CLI flag: -<prefix>.backoff-max-period
     *   [max_period: <duration> | default = 10s]
     */
    val maxPeriod: Duration? = null,
    /**
     *   # Number of times to backoff and retry before failing.
     *   # CLI flag: -<prefix>.backoff-retries
     *   [max_retries: <int> | default = 10]
     */
    val maxRetries: Int? = null,
)