package io.violabs.picard.starCharts.loki.cosStorageConfig

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class BackoffConfig(
    /**
     *   # Minimum backoff time when cos get Object.
     *   # CLI flag: -<prefix>.cos.min-backoff
     *   [min_period: <duration> | default = 100ms]
     */
    val minPeriod: Duration? = null,
    /**
     *   # Maximum backoff time when cos get Object.
     *   # CLI flag: -<prefix>.cos.max-backoff
     *   [max_period: <duration> | default = 3s]
     */
    val maxPeriod: Duration? = null,
    /**
     *   # Maximum number of times to retry when cos get Object.
     *   # CLI flag: -<prefix>.cos.max-retries
     *   [max_retries: <int> | default = 5]
     */
    val maxRetries: Int? = null,
)
