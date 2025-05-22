package io.violabs.picard.starCharts.loki.s3StorageConfig

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class BackoffConfig(
    /**
     * # Minimum backoff time when s3 get Object
     * # CLI flag: -<prefix>.s3.min-backoff
     * [min_period: <duration> | default = 100ms]
     */
    val minPeriod: Duration? = null,
    /**
     * # Maximum backoff time when s3 get Object
     * # CLI flag: -<prefix>.s3.max-backoff
     * [max_period: <duration> | default = 3s]
     */
    val maxPeriod: Duration? = null,
    /**
     * # Maximum number of times to retry for s3 GetObject or ObjectExists
     * # CLI flag: -<prefix>.s3.max-retries
     * [max_retries: <int> | default = 5]
     */
    val maxRetries: Int? = null,
    /**
     * # Disable forcing S3 dualstack endpoint usage.
     * # CLI flag: -<prefix>.s3.disable-dualstack
     * [disable_dualstack: <boolean> | default = false]
     */
    val disableDualstack: Boolean? = null
)