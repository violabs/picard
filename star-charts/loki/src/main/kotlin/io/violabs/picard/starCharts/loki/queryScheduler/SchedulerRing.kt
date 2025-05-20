package io.violabs.picard.starCharts.loki.queryScheduler

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Consul
import io.violabs.picard.starCharts.loki.ETCD
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL
data class SchedulerRing(
    val kvstore: KVStore? = null,
    /**
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -query-scheduler.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     */
    val heartbeatPeriod: Duration? = null,
    /**
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -query-scheduler.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     */
    val heartbeatTimeout: Duration? = null,
    /**
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -query-scheduler.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     */
    val tokensFilePath: String? = null,
    /**
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -query-scheduler.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     */
    val zoneAwarenessEnabled: Boolean? = null,
    /**
     *   # Instance ID to register in the ring.
     *   # CLI flag: -query-scheduler.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     */
    val instanceId: String? = null,
    /**
     *   # Name of network interface to read address from.
     *   # CLI flag: -query-scheduler.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     */
    val instanceInterfaceNames: List<String>? = null,
    /**
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -query-scheduler.ring.instance-port
     *   [instance_port: <int> | default = 0]
     */
    val instancePort: Int? = null,
    /**
     *   # IP address to advertise in the ring.
     *   # CLI flag: -query-scheduler.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     */
    val instanceAddr: String? = null,
    /**
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -query-scheduler.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     */
    val instanceAvailabilityZone: String? = null,
    /**
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -query-scheduler.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     */
    val instanceEnableIpv6: Boolean? = null,
) {
    @GeneratedDSL
    data class KVStore(
        /**
         *     # Backend storage to use for the ring. Supported values are: consul, etcd,
         *     # inmemory, memberlist, multi.
         *     # CLI flag: -query-scheduler.ring.store
         *     [store: <string> | default = "consul"]
         */
        val store: String? = null,
        /**
         *     # The prefix for the keys in the store. Should end with a /.
         *     # CLI flag: -query-scheduler.ring.prefix
         *     [prefix: <string> | default = "collectors/"]
         */
        val prefix: String? = null,
        /**
         *     # Configuration for a Consul client. Only applies if the selected kvstore is
         *     # consul.
         *     # The CLI flags prefix for this block configuration is: query-scheduler.ring
         *     [consul: <consul>]
         */
        val consul: Consul? = null,
        /**
         *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
         *     # is etcd.
         *     # The CLI flags prefix for this block configuration is: query-scheduler.ring
         *     [etcd: <etcd>]
         */
        val etcd: ETCD? = null,
        val multi: Multi? = null,
    ) {
        @GeneratedDSL
        data class Multi(
            /**
             *       # Primary backend storage used by multi-client.
             *       # CLI flag: -query-scheduler.ring.multi.primary
             *       [primary: <string> | default = ""]
             */
            val primary: String? = null,
            /**
             *       # Secondary backend storage used by multi-client.
             *       # CLI flag: -query-scheduler.ring.multi.secondary
             *       [secondary: <string> | default = ""]
             */
            val secondary: String? = null,
            /**
             *       # Mirror writes to secondary store.
             *       # CLI flag: -query-scheduler.ring.multi.mirror-enabled
             *       [mirror_enabled: <boolean> | default = false]
             */
            val mirrorEnabled: Boolean? = null,
            /**
             *       # Timeout for storing value to secondary store.
             *       # CLI flag: -query-scheduler.ring.multi.mirror-timeout
             *       [mirror_timeout: <duration> | default = 2s]
             */
            val mirrorTimeout: Duration? = null,
        )
    }
}
