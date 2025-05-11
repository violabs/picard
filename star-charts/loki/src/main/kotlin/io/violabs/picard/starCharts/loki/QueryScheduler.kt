package io.violabs.picard.starCharts.loki

class QueryScheduler(
    /**
     * # Maximum number of outstanding requests per tenant per query-scheduler.
     * # In-flight requests above this limit will fail with HTTP response status code
     * # 429.
     * # CLI flag: -query-scheduler.max-outstanding-requests-per-tenant
     * [max_outstanding_requests_per_tenant: <int> | default = 32000]
     *
     * # Maximum number of levels of nesting of hierarchical queues. 0 means that
     * # hierarchical queues are disabled.
     * # CLI flag: -query-scheduler.max-queue-hierarchy-levels
     * [max_queue_hierarchy_levels: <int> | default = 3]
     *
     * # If a querier disconnects without sending notification about graceful shutdown,
     * # the query-scheduler will keep the querier in the tenant's shard until the
     * # forget delay has passed. This feature is useful to reduce the blast radius
     * # when shuffle-sharding is enabled.
     * # CLI flag: -query-scheduler.querier-forget-delay
     * [querier_forget_delay: <duration> | default = 0s]
     *
     * # This configures the gRPC client used to report errors back to the
     * # query-frontend.
     * # The CLI flags prefix for this block configuration is:
     * # query-scheduler.grpc-client-config
     * [grpc_client_config: <grpc_client>]
     *
     * # Set to true to have the query schedulers create and place themselves in a
     * # ring. If no frontend_address or scheduler_address are present anywhere else in
     * # the configuration, Loki will toggle this value to true.
     * # CLI flag: -query-scheduler.use-scheduler-ring
     * [use_scheduler_ring: <boolean> | default = false]
     *
     * # The hash ring configuration. This option is required only if
     * # use_scheduler_ring is true.
     * scheduler_ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -query-scheduler.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -query-scheduler.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: query-scheduler.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: query-scheduler.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -query-scheduler.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -query-scheduler.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -query-scheduler.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -query-scheduler.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -query-scheduler.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     *
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -query-scheduler.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -query-scheduler.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     *
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -query-scheduler.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     *
     *   # Instance ID to register in the ring.
     *   # CLI flag: -query-scheduler.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -query-scheduler.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -query-scheduler.ring.instance-port
     *   [instance_port: <int> | default = 0]
     *
     *   # IP address to advertise in the ring.
     *   # CLI flag: -query-scheduler.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     *
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -query-scheduler.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     *
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -query-scheduler.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     */
) {
}