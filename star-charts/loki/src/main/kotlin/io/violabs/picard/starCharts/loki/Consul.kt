package io.violabs.picard.starCharts.loki

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Consul(
    /**
     * # Hostname and port of Consul.
     * # CLI flag: -<prefix>.consul.hostname
     * [host: <string> | default = "localhost:8500"]
     */
    val host: String? = null,
    /**
     * # ACL Token used to interact with Consul.
     * # CLI flag: -<prefix>.consul.acl-token
     * [acl_token: <string> | default = ""]
     */
    val aclToken: String? = null,
    /**
     * # HTTP timeout when talking to Consul
     * # CLI flag: -<prefix>.consul.client-timeout
     * [http_client_timeout: <duration> | default = 20s]
     */
    val httpClientTimeout: Duration? = null,
    /**
     * # Enable consistent reads to Consul.
     * # CLI flag: -<prefix>.consul.consistent-reads
     * [consistent_reads: <boolean> | default = false]
     */
    val consistentReads: Boolean? = null,
    /**
     * # Rate limit when watching key or prefix in Consul, in requests per second. 0
     * # disables the rate limit.
     * # CLI flag: -<prefix>.consul.watch-rate-limit
     * [watch_rate_limit: <float> | default = 1]
     */
    val watchRateLimit: Float? = null,
    /**
     * # Burst size used in rate limit. Values less than 1 are treated as 1.
     * # CLI flag: -<prefix>.consul.watch-burst-size
     * [watch_burst_size: <int> | default = 1]
     */
    val watchBurstSize: Int? = null,
    /**
     * # Maximum duration to wait before retrying a Compare And Swap (CAS) operation.
     * # CLI flag: -<prefix>.consul.cas-retry-delay
     * [cas_retry_delay: <duration> | default = 1s]
     */
    val casRetryDelay: Duration? = null,
)
