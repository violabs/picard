package io.violabs.picard.starCharts.loki

class CacheConfig(
    /**
     * # The default validity of entries for caches unless overridden.
     * # CLI flag: -<prefix>.default-validity
     * [default_validity: <duration> | default = 1h]
     *
     * background:
     *   # At what concurrency to write back to cache.
     *   # CLI flag: -<prefix>.background.write-back-concurrency
     *   [writeback_goroutines: <int> | default = 1]
     *
     *   # How many key batches to buffer for background write-back. Default is large
     *   # to prefer size based limiting.
     *   # CLI flag: -<prefix>.background.write-back-buffer
     *   [writeback_buffer: <int> | default = 500000]
     *
     *   # Size limit in bytes for background write-back.
     *   # CLI flag: -<prefix>.background.write-back-size-limit
     *   [writeback_size_limit: <int> | default = 500MB]
     *
     * memcached:
     *   # How long keys stay in the memcache.
     *   # CLI flag: -<prefix>.memcached.expiration
     *   [expiration: <duration> | default = 0s]
     *
     *   # How many keys to fetch in each batch.
     *   # CLI flag: -<prefix>.memcached.batchsize
     *   [batch_size: <int> | default = 4]
     *
     *   # Maximum active requests to memcache.
     *   # CLI flag: -<prefix>.memcached.parallelism
     *   [parallelism: <int> | default = 5]
     *
     * memcached_client:
     *   # Hostname for memcached service to use. If empty and if addresses is unset,
     *   # no memcached will be used.
     *   # CLI flag: -<prefix>.memcached.hostname
     *   [host: <string> | default = ""]
     *
     *   # SRV service used to discover memcache servers.
     *   # CLI flag: -<prefix>.memcached.service
     *   [service: <string> | default = "memcached"]
     *
     *   # Comma separated addresses list in DNS Service Discovery format:
     *   # https://grafana.com/docs/mimir/latest/configure/about-dns-service-discovery/#supported-discovery-modes
     *   # CLI flag: -<prefix>.memcached.addresses
     *   [addresses: <string> | default = ""]
     *
     *   # Maximum time to wait before giving up on memcached requests.
     *   # CLI flag: -<prefix>.memcached.timeout
     *   [timeout: <duration> | default = 100ms]
     *
     *   # Maximum number of idle connections in pool.
     *   # CLI flag: -<prefix>.memcached.max-idle-conns
     *   [max_idle_conns: <int> | default = 16]
     *
     *   # The maximum size of an item stored in memcached. Bigger items are not
     *   # stored. If set to 0, no maximum size is enforced.
     *   # CLI flag: -<prefix>.memcached.max-item-size
     *   [max_item_size: <int> | default = 0]
     *
     *   # Period with which to poll DNS for memcache servers.
     *   # CLI flag: -<prefix>.memcached.update-interval
     *   [update_interval: <duration> | default = 1m]
     *
     *   # Use consistent hashing to distribute to memcache servers.
     *   # CLI flag: -<prefix>.memcached.consistent-hash
     *   [consistent_hash: <boolean> | default = true]
     *
     *   # Trip circuit-breaker after this number of consecutive dial failures (if zero
     *   # then circuit-breaker is disabled).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-consecutive-failures
     *   [circuit_breaker_consecutive_failures: <int> | default = 10]
     *
     *   # Duration circuit-breaker remains open after tripping (if zero then 60
     *   # seconds is used).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-timeout
     *   [circuit_breaker_timeout: <duration> | default = 10s]
     *
     *   # Reset circuit-breaker counts after this long (if zero then never reset).
     *   # CLI flag: -<prefix>.memcached.circuit-breaker-interval
     *   [circuit_breaker_interval: <duration> | default = 10s]
     *
     *   # Enable connecting to Memcached with TLS.
     *   # CLI flag: -<prefix>.memcached.tls-enabled
     *   [tls_enabled: <boolean> | default = false]
     *
     *   # The TLS configuration.
     *   # The CLI flags prefix for this block configuration is:
     *   # store.index-cache-write.memcached
     *   [<tls_config>]
     *
     * redis:
     *   # Redis Server or Cluster configuration endpoint to use for caching. A
     *   # comma-separated list of endpoints for Redis Cluster or Redis Sentinel. If
     *   # empty, no redis will be used.
     *   # CLI flag: -<prefix>.redis.endpoint
     *   [endpoint: <string> | default = ""]
     *
     *   # Redis Sentinel master name. An empty string for Redis Server or Redis
     *   # Cluster.
     *   # CLI flag: -<prefix>.redis.master-name
     *   [master_name: <string> | default = ""]
     *
     *   # Maximum time to wait before giving up on redis requests.
     *   # CLI flag: -<prefix>.redis.timeout
     *   [timeout: <duration> | default = 500ms]
     *
     *   # How long keys stay in the redis.
     *   # CLI flag: -<prefix>.redis.expiration
     *   [expiration: <duration> | default = 0s]
     *
     *   # Database index.
     *   # CLI flag: -<prefix>.redis.db
     *   [db: <int> | default = 0]
     *
     *   # Maximum number of connections in the pool.
     *   # CLI flag: -<prefix>.redis.pool-size
     *   [pool_size: <int> | default = 0]
     *
     *   # Username to use when connecting to redis.
     *   # CLI flag: -<prefix>.redis.username
     *   [username: <string> | default = ""]
     *
     *   # Password to use when connecting to redis.
     *   # CLI flag: -<prefix>.redis.password
     *   [password: <string> | default = ""]
     *
     *   # Enable connecting to redis with TLS.
     *   # CLI flag: -<prefix>.redis.tls-enabled
     *   [tls_enabled: <boolean> | default = false]
     *
     *   # Skip validating server certificate.
     *   # CLI flag: -<prefix>.redis.tls-insecure-skip-verify
     *   [tls_insecure_skip_verify: <boolean> | default = false]
     *
     *   # Close connections after remaining idle for this duration. If the value is
     *   # zero, then idle connections are not closed.
     *   # CLI flag: -<prefix>.redis.idle-timeout
     *   [idle_timeout: <duration> | default = 0s]
     *
     *   # Close connections older than this duration. If the value is zero, then the
     *   # pool does not close connections based on age.
     *   # CLI flag: -<prefix>.redis.max-connection-age
     *   [max_connection_age: <duration> | default = 0s]
     *
     *   # By default, the Redis client only reads from the master node. Enabling this
     *   # option can lower pressure on the master node by randomly routing read-only
     *   # commands to the master and any available replicas.
     *   # CLI flag: -<prefix>.redis.route-randomly
     *   [route_randomly: <boolean> | default = false]
     *
     * embedded_cache:
     *   # Whether embedded cache is enabled.
     *   # CLI flag: -<prefix>.embedded-cache.enabled
     *   [enabled: <boolean> | default = false]
     *
     *   # Maximum memory size of the cache in MB.
     *   # CLI flag: -<prefix>.embedded-cache.max-size-mb
     *   [max_size_mb: <int> | default = 100]
     *
     *   # Maximum number of entries in the cache.
     *   # CLI flag: -<prefix>.embedded-cache.max-size-items
     *   [max_size_items: <int> | default = 0]
     *
     *   # The time to live for items in the cache before they get purged.
     *   # CLI flag: -<prefix>.embedded-cache.ttl
     *   [ttl: <duration> | default = 1h]
     */
) {
}