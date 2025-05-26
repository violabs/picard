package io.violabs.picard.starCharts.loki.ingester

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDsl
data class Lifecycler(
    val ring: Ring? = null,
    /**
     *   # Number of tokens for each ingester.
     *   # CLI flag: -ingester.num-tokens
     *   [num_tokens: <int> | default = 128]
     */
    val numTokens: Int? = null,
    /**
     *   # Period at which to heartbeat to consul. 0 = disabled.
     *   # CLI flag: -ingester.heartbeat-period
     *   [heartbeat_period: <duration> | default = 5s]
     */
    val heartbeatPeriod: Duration? = null,
    /**
     *   # Heartbeat timeout after which instance is assumed to be unhealthy. 0 =
     *   # disabled.
     *   # CLI flag: -ingester.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     */
    val heartbeatTimeout: Duration? = null,
    /**
     *   # Observe tokens after generating to resolve collisions. Useful when using
     *   # gossiping ring.
     *   # CLI flag: -ingester.observe-period
     *   [observe_period: <duration> | default = 0s]
     */
    val observePeriod: Duration? = null,
    /**
     *   # Period to wait for a claim from another member; will join automatically
     *   # after this.
     *   # CLI flag: -ingester.join-after
     *   [join_after: <duration> | default = 0s]
     */
    val joinAfter: Duration? = null,
    /**
     *   # Minimum duration to wait after the internal readiness checks have passed but
     *   # before succeeding the readiness endpoint. This is used to slowdown
     *   # deployment controllers (eg. Kubernetes) after an instance is ready and
     *   # before they proceed with a rolling update, to give the rest of the cluster
     *   # instances enough time to receive ring updates.
     *   # CLI flag: -ingester.min-ready-duration
     *   [min_ready_duration: <duration> | default = 15s]
     */
    val minReadyDuration: Duration? = null,
    /**
     *   # Name of network interface to read address from.
     *   # CLI flag: -ingester.lifecycler.interface
     *   [interface_names: <list of strings> | default = [<private network interfaces>]]
     */
    val interfaceNames: List<String>? = null,
    /**
     *   # Enable IPv6 support. Required to make use of IP addresses from IPv6
     *   # interfaces.
     *   # CLI flag: -ingester.enable-inet6
     *   [enable_inet6: <boolean> | default = false]
     */
    val enableInet6: Boolean? = null,
    /**
     *   # Duration to sleep for before exiting, to ensure metrics are scraped.
     *   # CLI flag: -ingester.final-sleep
     *   [final_sleep: <duration> | default = 0s]
     */
    val finalSleep: Duration? = null,
    /**
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -ingester.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     */
    val tokensFilePath: String? = null,
    /**
     *   # The availability zone where this instance is running.
     *   # CLI flag: -ingester.availability-zone
     *   [availability_zone: <string> | default = ""]
     */
    val availabilityZone: String? = null,
    /**
     *   # Unregister from the ring upon clean shutdown. It can be useful to disable
     *   # for rolling restarts with consistent naming in conjunction with
     *   # -distributor.extend-writes=false.
     *   # CLI flag: -ingester.unregister-on-shutdown
     *   [unregister_on_shutdown: <boolean> | default = true]
     */
    val unregisterOnShutdown: Boolean? = null,
    /**
     *   # When enabled the readiness probe succeeds only after all instances are
     *   # ACTIVE and healthy in the ring, otherwise only the instance itself is
     *   # checked. This option should be disabled if in your cluster multiple
     *   # instances can be rolled out simultaneously, otherwise rolling updates may be
     *   # slowed down.
     *   # CLI flag: -ingester.readiness-check-ring-health
     *   [readiness_check_ring_health: <boolean> | default = true]
     */
    val readinessCheckRingHealth: Boolean? = null,
    /**
     *   # IP address to advertise in the ring.
     *   # CLI flag: -ingester.lifecycler.addr
     *   [address: <string> | default = ""]
     */
    val address: String? = null,
    /**
     *   # port to advertise in consul (defaults to server.grpc-listen-port).
     *   # CLI flag: -ingester.lifecycler.port
     *   [port: <int> | default = 0]
     */
    val port: Int? = null,
    /**
     *   # ID to register in the ring.
     *   # CLI flag: -ingester.lifecycler.ID
     *   [id: <string> | default = "<hostname>"]
     */
    val id: String? = null
)