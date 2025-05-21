package io.violabs.picard.starCharts.loki.cacheConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL
data class Redis(
    /**
     *   # Redis Server or Cluster configuration endpoint to use for caching. A
     *   # comma-separated list of endpoints for Redis Cluster or Redis Sentinel. If
     *   # empty, no redis will be used.
     *   # CLI flag: -<prefix>.redis.endpoint
     *   [endpoint: <string> | default = ""]
     */
    val endpoint: String? = null,
    /**
     *   # Redis Sentinel master name. An empty string for Redis Server or Redis Cluster.
     *   # CLI flag: -<prefix>.redis.master-name
     *   [master_name: <string> | default = ""]
     */
    val masterName: String? = null,
    /**
     *   # Maximum time to wait before giving up on redis requests.
     *   # CLI flag: -<prefix>.redis.timeout
     *   [timeout: <duration> | default = 500ms]
     */
    val timeout: Duration? = null,
    /**
     *   # How long keys stay in the redis.
     *   # CLI flag: -<prefix>.redis.expiration
     *   [expiration: <duration> | default = 0s]
     */
    val expiration: Duration? = null,
    /**
     *   # Database index.
     *   # CLI flag: -<prefix>.redis.db
     *   [db: <int> | default = 0]
     */
    val db: Int? = null,
    /**
     *   # Maximum number of connections in the pool.
     *   # CLI flag: -<prefix>.redis.pool-size
     *   [pool_size: <int> | default = 0]
     */
    val poolSize: Int? = null,
    /**
     *   # Username to use when connecting to redis.
     *   # CLI flag: -<prefix>.redis.username
     *   [username: <string> | default = ""]
     */
    val username: String? = null,
    /**
     *   # Password to use when connecting to redis.
     *   # CLI flag: -<prefix>.redis.password
     *   [password: <string> | default = ""]
     */
    val password: String? = null,
    /**
     *   # Enable connecting to redis with TLS.
     *   # CLI flag: -<prefix>.redis.tls-enabled
     *   [tls_enabled: <boolean> | default = false]
     */
    val tlsEnabled: Boolean? = null,
    /**
     *   # Skip validating server certificate.
     *   # CLI flag: -<prefix>.redis.tls-insecure-skip-verify
     *   [tls_insecure_skip_verify: <boolean> | default = false]
     */
    val tlsInsecureSkipVerify: Boolean? = null,
    /**
     *   # Close connections after remaining idle for this duration. If the value is
     *   # zero, then idle connections are not closed.
     *   # CLI flag: -<prefix>.redis.idle-timeout
     *   [idle_timeout: <duration> | default = 0s]
     */
    val idleTimeout: Duration? = null,
    /**
     *   # Close connections older than this duration. If the value is zero, then the
     *   # pool does not close connections based on age.
     *   # CLI flag: -<prefix>.redis.max-connection-age
     *   [max_connection_age: <duration> | default = 0s]
     */
    val maxConnectionAge: Duration? = null,
    /**
     *   # By default, the Redis client only reads from the master node. Enabling this
     *   # option can lower pressure on the master node by randomly routing read-only
     *   # commands to the master and any available replicas.
     *   # CLI flag: -<prefix>.redis.route-randomly
     *   [route_randomly: <boolean> | default = false]
     */
    val routeRandomly: Boolean? = null,
)
