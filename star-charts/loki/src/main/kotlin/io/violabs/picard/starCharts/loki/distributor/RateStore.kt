package io.violabs.picard.starCharts.loki.distributor

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class RateStore(
    /**
     * # The max number of concurrent requests to make to ingester stream apis
     * # CLI flag: -distributor.rate-store.max-request-parallelism
     * [max_request_parallelism: <int> | default = 200]
     */
    val maxRequestParallelism: Int? = null,
    /**
     * # The interval on which distributors will update current stream rates from
     * # ingesters
     * # CLI flag: -distributor.rate-store.stream-rate-update-interval
     * [stream_rate_update_interval: <duration> | default = 1s]
     */
    val streamRateUpdateInterval: Duration? = null,
    /**
     * # Timeout for communication between distributors and any given ingester when
     * # updating rates
     * # CLI flag: -distributor.rate-store.ingester-request-timeout
     * [ingester_request_timeout: <duration> | default = 500ms]
     */
    val ingesterRequestTimeout: Duration? = null,
    /**
     * # If enabled, detailed logs and spans will be emitted.
     * # CLI flag: -distributor.rate-store.debug
     * [debug: <boolean> | default = false]
     */
    val debug: Boolean? = null
)
