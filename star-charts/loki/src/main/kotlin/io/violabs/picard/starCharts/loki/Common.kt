package io.violabs.picard.starCharts.loki

class Common(
    /**
     * # prefix for the path
     * # CLI flag: -common.path-prefix
     * [path_prefix: <string> | default = ""]
     *
     * storage:
     *   # The s3_storage_config block configures the connection to Amazon S3 object
     *   # storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [s3: <s3_storage_config>]
     *
     *   # The gcs_storage_config block configures the connection to Google Cloud
     *   # Storage object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [gcs: <gcs_storage_config>]
     *
     *   # The azure_storage_config block configures the connection to Azure object
     *   # storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [azure: <azure_storage_config>]
     *
     *   # The alibabacloud_storage_config block configures the connection to Alibaba
     *   # Cloud Storage object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [alibabacloud: <alibabacloud_storage_config>]
     *
     *   # The bos_storage_config block configures the connection to Baidu Object
     *   # Storage (BOS) object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [bos: <bos_storage_config>]
     *
     *   # The swift_storage_config block configures the connection to OpenStack Object
     *   # Storage (Swift) object storage backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [swift: <swift_storage_config>]
     *
     *   filesystem:
     *     # Directory to store chunks in.
     *     # CLI flag: -common.storage.filesystem.chunk-directory
     *     [chunks_directory: <string> | default = ""]
     *
     *     # Directory to store rules in.
     *     # CLI flag: -common.storage.filesystem.rules-directory
     *     [rules_directory: <string> | default = ""]
     *
     *   hedging:
     *     # If set to a non-zero value a second request will be issued at the provided
     *     # duration. Default is 0 (disabled)
     *     # CLI flag: -common.storage.hedge-requests-at
     *     [at: <duration> | default = 0s]
     *
     *     # The maximum of hedge requests allowed.
     *     # CLI flag: -common.storage.hedge-requests-up-to
     *     [up_to: <int> | default = 2]
     *
     *     # The maximum of hedge requests allowed per seconds.
     *     # CLI flag: -common.storage.hedge-max-per-second
     *     [max_per_second: <int> | default = 5]
     *
     *   # The cos_storage_config block configures the connection to IBM Cloud Object
     *   # Storage (COS) backend.
     *   # The CLI flags prefix for this block configuration is: common.storage
     *   [cos: <cos_storage_config>]
     *
     *   congestion_control:
     *     # Use storage congestion control (default: disabled).
     *     # CLI flag: -common.storage.congestion-control.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     controller:
     *       # Congestion control strategy to use (default: none, options: 'aimd').
     *       # CLI flag: -common.storage.congestion-control.strategy
     *       [strategy: <string> | default = ""]
     *
     *       aimd:
     *         # AIMD starting throughput window size: how many requests can be sent
     *         # per second (default: 2000).
     *         # CLI flag: -common.storage.congestion-control.strategy.aimd.start
     *         [start: <int> | default = 2000]
     *
     *         # AIMD maximum throughput window size: upper limit of requests sent per
     *         # second (default: 10000).
     *         # CLI flag: -common.storage.congestion-control.strategy.aimd.upper-bound
     *         [upper_bound: <int> | default = 10000]
     *
     *         # AIMD backoff factor when upstream service is throttled to decrease
     *         # number of requests sent per second (default: 0.5).
     *         # CLI flag: -common.storage.congestion-control.strategy.aimd.backoff-factor
     *         [backoff_factor: <float> | default = 0.5]
     *
     *     retry:
     *       # Congestion control retry strategy to use (default: none, options:
     *       # 'limited').
     *       # CLI flag: -common.storage.congestion-control.retry.strategy
     *       [strategy: <string> | default = ""]
     *
     *       # Maximum number of retries allowed.
     *       # CLI flag: -common.storage.congestion-control.retry.strategy.limited.limit
     *       [limit: <int> | default = 2]
     *
     *     hedging:
     *       config:
     *         [at: <duration>]
     *
     *         [up_to: <int>]
     *
     *         [max_per_second: <int>]
     *
     *       # Congestion control hedge strategy to use (default: none, options:
     *       # 'limited').
     *       # CLI flag: -common.storage.congestion-control.hedge.strategy
     *       [strategy: <string> | default = ""]
     *
     *   # The thanos_object_store_config block configures the connection to object
     *   # storage backend using thanos-io/objstore clients. This will become the
     *   # default way of configuring object store clients in future releases.
     *   # Currently this is opt-in and takes effect only when `-use-thanos-objstore`
     *   # is set to true.
     *   # The CLI flags prefix for this block configuration is:
     *   # common.storage.object-store
     *   [object_store: <thanos_object_store_config>]
     *
     * [persist_tokens: <boolean>]
     *
     * [replication_factor: <int>]
     *
     * ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -common.storage.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -common.storage.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: common.storage.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: common.storage.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -common.storage.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -common.storage.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -common.storage.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -common.storage.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -common.storage.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     *
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -common.storage.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -common.storage.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     *
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -common.storage.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     *
     *   # Number of tokens to own in the ring.
     *   # CLI flag: -common.storage.ring.num-tokens
     *   [num_tokens: <int> | default = 128]
     *
     *   # Factor for data replication.
     *   # CLI flag: -common.storage.ring.replication-factor
     *   [replication_factor: <int> | default = 3]
     *
     *   # Instance ID to register in the ring.
     *   # CLI flag: -common.storage.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -common.storage.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -common.storage.ring.instance-port
     *   [instance_port: <int> | default = 0]
     *
     *   # IP address to advertise in the ring.
     *   # CLI flag: -common.storage.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     *
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -common.storage.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     *
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -common.storage.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     *
     * [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     * [instance_addr: <string> | default = ""]
     *
     * # the http address of the compactor in the form http://host:port
     * # CLI flag: -common.compactor-address
     * [compactor_address: <string> | default = ""]
     *
     * # the grpc address of the compactor in the form host:port
     * # CLI flag: -common.compactor-grpc-address
     * [compactor_grpc_address: <string> | default = ""]
     */
) {
}