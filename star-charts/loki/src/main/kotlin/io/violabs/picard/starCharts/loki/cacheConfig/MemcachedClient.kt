package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.tls.TLSConfig

@GeneratedDSL
data class MemcachedClient(
    /**
     *   # Hostname for memcached service to use. If empty and if addresses is unset,
     *   # no memcached will be used.
     *   # CLI flag: -<prefix>.memcached.hostname
     *   [host: <string> | default = ""]
     */
    val host: String? = null,
    /**
     *   # SRV service used to discover memcache servers.
     *   # CLI flag: -<prefix>.memcached.service
     *   [service: <string> | default = "memcached"]
     */
    val service: String? = null,
    /**
     *   # Comma separated addresses list in DNS Service Discovery format:
     *   # https://grafana.com/docs/mimir/latest/configure/about-dns-service-discovery/#supported-discovery-modes
     *   # CLI flag: -<prefix>.memcached.addresses
     *   [addresses: <string> | default = ""]
     */
    val addresses: String? = null,
    /**
     *   # Maximum time to wait before giving up on memcached requests.
     *   # CLI flag: -<prefix>.memcached.timeout
     *   [timeout: <duration> | default = 100ms]
     */
    val timeout: Duration? = null,
    /**
     *   # Maximum number of idle connections in pool.
     *   # CLI flag: -<prefix>.memcached.max-idle-conns
     *   [max_idle_conns: <int> | default = 16]
     */
    val maxIdleConns: Int? = null,
    /**
     *   # The maximum size of an item stored in memcached. Bigger items are not
     *   # stored. If set to 0, no maximum size is enforced.
     *   # CLI flag: -<prefix>.memcached.max-item-size
     *   [max_item_size: <int> | default = 0]
     */
    val maxItemSize: Int? = null,
    /**
     *   # Period with which to poll DNS for memcache servers.
     *   # CLI flag: -<prefix>.memcached.update-interval
     *   [update_interval: <duration> | default = 1m]
     */
    val updateInterval: Duration? = null,
    /**
     *   # Use consistent hashing to distribute to memcache servers.
     *   # CLI flag: -<prefix>.memcached.consistent-hash
     *   [consistent_hash: <boolean> | default = true]
     */
    val consistentHash: Boolean? = null,
    /**
     *   # Trip circuit-breaker after this number of consecutive dial failures (if zero
     *   # then circuit-breaker is disabled).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-consecutive-failures
     *   [circuit_breaker_consecutive_failures: <int> | default = 10]
     */
    val circuitBreakerConsecutiveFailures: Int? = null,
    /**
     *   # Duration circuit-breaker remains open after tripping (if zero then 60
     *   # seconds is used).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-timeout
     *   [circuit_breaker_timeout: <duration> | default = 10s]
     */
    val circuitBreakerTimeout: Duration? = null,
    /**
     *   # Reset circuit-breaker counts after this long (if zero then never reset).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-interval
     *   [circuit_breaker_interval: <duration> | default = 10s]
     */
    val circuitBreakerInterval: Duration? = null,
    /**
     *   # Enable connecting to Memcached with TLS.
     *   # CLI flag: -<prefix>.memcached.tls-enabled
     *   [tls_enabled: <boolean> | default = false]
     */
    val tlsEnabled: Boolean? = null,
    /**
     *   # The TLS configuration.
     *   # The CLI flags prefix for this block configuration is:
     *   # store.index-cache-write.memcached
     *   [<tls_config>]
     */
    val tlsConfig: TLSConfig? = null,
)
