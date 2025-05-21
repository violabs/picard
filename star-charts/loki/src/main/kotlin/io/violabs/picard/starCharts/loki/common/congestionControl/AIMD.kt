package io.violabs.picard.starCharts.loki.common.congestionControl

data class AIMD(
    /**
     * # AIMD starting throughput window size: how many requests can be sent
     * # per second (default: 2000).
     * # CLI flag: -common.storage.congestion-control.strategy.aimd.start
     * [start: <int> | default = 2000]
     */
    val start: Int? = null,
    /**
     * # AIMD maximum throughput window size: upper limit of requests sent per
     * # second (default: 10000).
     * # CLI flag: -common.storage.congestion-control.strategy.aimd.upper-bound
     * [upper_bound: <int> | default = 10000]
     */
    val upperBound: Int? = null,
    /**
     * # AIMD backoff factor when upstream service is throttled to decrease
     * # number of requests sent per second (default: 0.5).
     * # CLI flag: -common.storage.congestion-control.strategy.aimd.backoff-factor
     * [backoff_factor: <float> | default = 0.5]
     */
    val backoffFactor: Float? = null,
)