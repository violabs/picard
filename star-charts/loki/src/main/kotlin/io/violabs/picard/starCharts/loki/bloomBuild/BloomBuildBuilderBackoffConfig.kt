package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class BloomBuildBuilderBackoffConfig(
    /**
     * # Minimum delay when backing off.
     *  # CLI flag: -bloom-build.builder.backoff.backoff-min-period
     * [min_period: <duration> | default = 100ms]
     */
    val minPeriod: Duration? = null,
    /**
     * # Maximum delay when backing off.
     * # CLI flag: -bloom-build.builder.backoff.backoff-max-period
     * [max_period: <duration> | default = 10s]
     */
    val maxPeriod: Duration? = null,
    /**
     * # Number of times to backoff and retry before failing.
     * # CLI flag: -bloom-build.builder.backoff.backoff-retries
     * [max_retries: <int> | default = 10]
     */
    val maxRetries: Int? = null
)