package io.violabs.picard.starCharts.loki.ring

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class Ring(
    val kvstore: KVStore? = null,
    /**
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -index-gateway.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     */
    val heartbeatPeriod: Duration? = null,
    /**
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -index-gateway.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     */
    val heartbeatTimeout: Duration? = null,
    /**
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -index-gateway.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     */
    val tokensFilePath: String? = null,
    /**
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -index-gateway.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     */
    val zoneAwarenessEnabled: Boolean? = null,
    /**
     * # Comma-separated list of zones to exclude from the ring. Instances in
     * # excluded zones will be filtered out from the ring.
     * # CLI flag: -distributor.excluded-zones
     * [excluded_zones: <string> | default = ""]
     */
    val excludedZones: String? = null,
    /**
     *   # Number of tokens to own in the ring.
     *   # CLI flag: -common.storage.ring.num-tokens
     *   [num_tokens: <int> | default = 128]
     */
    val numTokens: Int? = null,
    /**
     *   # Deprecated: How many index gateway instances are assigned to each tenant.
     *   # Use -index-gateway.shard-size instead.
     *   # CLI flag: -replication-factor
     *   [replication_factor: <int> | default = 3]
     */
    val replicationFactor: Int? = null,
    /**
     *   # Instance ID to register in the ring.
     *   # CLI flag: -index-gateway.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     */
    val instanceId: String? = null,
    /**
     *   # Name of network interface to read address from.
     *   # CLI flag: -index-gateway.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     */
    val instanceInterfaceNames: List<String>? = null,
    /**
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -index-gateway.ring.instance-port
     *   [instance_port: <int> | default = 0]
     */
    val instancePort: Int? = null,
    /**
     *   # IP address to advertise in the ring.
     *   # CLI flag: -index-gateway.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     */
    @JsonProperty("instanceAddr")
    val instanceAddress: String? = null,
    /**
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -index-gateway.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     */
    val instanceAvailabilityZone: String? = null,
    /**
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -index-gateway.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     */
    val instanceEnableIpv6: Boolean? = null
)
