package io.violabs.picard.starCharts.loki

class IndexGateway(
    /**
     * # Defines in which mode the index gateway server will operate (default to
     * # 'simple'). It supports two modes:
     * # - 'simple': an index gateway server instance is responsible for handling,
     * # storing and returning requests for all indices for all tenants.
     * # - 'ring': an index gateway server instance is responsible for a subset of
     * # tenants instead of all tenants.
     * # CLI flag: -index-gateway.mode
     * [mode: <string> | default = "simple"]
     *
     * # Defines the ring to be used by the index gateway servers and clients in case
     * # the servers are configured to run in 'ring' mode. In case this isn't
     * # configured, this block supports inheriting configuration from the common ring
     * # section.
     * ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -index-gateway.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -index-gateway.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: index-gateway.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: index-gateway.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -index-gateway.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -index-gateway.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -index-gateway.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -index-gateway.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -index-gateway.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     *
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -index-gateway.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -index-gateway.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     *
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -index-gateway.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     *
     *   # Deprecated: How many index gateway instances are assigned to each tenant.
     *   # Use -index-gateway.shard-size instead. The shard size is also a per-tenant
     *   # setting.
     *   # CLI flag: -replication-factor
     *   [replication_factor: <int> | default = 3]
     *
     *   # Instance ID to register in the ring.
     *   # CLI flag: -index-gateway.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -index-gateway.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -index-gateway.ring.instance-port
     *   [instance_port: <int> | default = 0]
     *
     *   # IP address to advertise in the ring.
     *   # CLI flag: -index-gateway.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     *
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -index-gateway.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     *
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -index-gateway.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     */
) {
}