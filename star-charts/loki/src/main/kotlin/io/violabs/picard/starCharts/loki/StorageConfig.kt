package io.violabs.picard.starCharts.loki

class StorageConfig(
    /**
     * # The alibabacloud_storage_config block configures the connection to Alibaba
     * # Cloud Storage object storage backend.
     * [alibabacloud: <alibabacloud_storage_config>]
     *
     * # The aws_storage_config block configures the connection to dynamoDB and S3
     * # object storage. Either one of them or both can be configured.
     * [aws: <aws_storage_config>]
     *
     * # The azure_storage_config block configures the connection to Azure object
     * # storage backend.
     * [azure: <azure_storage_config>]
     *
     * # The bos_storage_config block configures the connection to Baidu Object Storage
     * # (BOS) object storage backend.
     * [bos: <bos_storage_config>]
     *
     * # Deprecated: Configures storing indexes in Bigtable. Required fields only
     * # required when bigtable is defined in config.
     * bigtable:
     *   # Bigtable project ID.
     *   # CLI flag: -bigtable.project
     *   [project: <string> | default = ""]
     *
     *   # Bigtable instance ID. Please refer to
     *   # https://cloud.google.com/docs/authentication/production for more information
     *   # about how to configure authentication.
     *   # CLI flag: -bigtable.instance
     *   [instance: <string> | default = ""]
     *
     *   # The grpc_client block configures the gRPC client used to communicate between
     *   # a client and server component in Loki.
     *   # The CLI flags prefix for this block configuration is: bigtable
     *   [grpc_client_config: <grpc_client>]
     *
     *   # If enabled, once a tables info is fetched, it is cached.
     *   # CLI flag: -bigtable.table-cache.enabled
     *   [table_cache_enabled: <boolean> | default = true]
     *
     *   # Duration to cache tables before checking again.
     *   # CLI flag: -bigtable.table-cache.expiration
     *   [table_cache_expiration: <duration> | default = 30m]
     *
     * # Configures storing chunks in GCS. Required fields only required when gcs is
     * # defined in config.
     * [gcs: <gcs_storage_config>]
     *
     * # Deprecated: Configures storing chunks and/or the index in Cassandra.
     * cassandra:
     *   # Comma-separated hostnames or IPs of Cassandra instances.
     *   # CLI flag: -cassandra.addresses
     *   [addresses: <string> | default = ""]
     *
     *   # Port that Cassandra is running on
     *   # CLI flag: -cassandra.port
     *   [port: <int> | default = 9042]
     *
     *   # Keyspace to use in Cassandra.
     *   # CLI flag: -cassandra.keyspace
     *   [keyspace: <string> | default = ""]
     *
     *   # Consistency level for Cassandra.
     *   # CLI flag: -cassandra.consistency
     *   [consistency: <string> | default = "QUORUM"]
     *
     *   # Replication factor to use in Cassandra.
     *   # CLI flag: -cassandra.replication-factor
     *   [replication_factor: <int> | default = 3]
     *
     *   # Instruct the cassandra driver to not attempt to get host info from the
     *   # system.peers table.
     *   # CLI flag: -cassandra.disable-initial-host-lookup
     *   [disable_initial_host_lookup: <boolean> | default = false]
     *
     *   # Use SSL when connecting to cassandra instances.
     *   # CLI flag: -cassandra.ssl
     *   [SSL: <boolean> | default = false]
     *
     *   # Require SSL certificate validation.
     *   # CLI flag: -cassandra.host-verification
     *   [host_verification: <boolean> | default = true]
     *
     *   # Policy for selecting Cassandra host. Supported values are: round-robin,
     *   # token-aware.
     *   # CLI flag: -cassandra.host-selection-policy
     *   [host_selection_policy: <string> | default = "round-robin"]
     *
     *   # Path to certificate file to verify the peer.
     *   # CLI flag: -cassandra.ca-path
     *   [CA_path: <string> | default = ""]
     *
     *   # Path to certificate file used by TLS.
     *   # CLI flag: -cassandra.tls-cert-path
     *   [tls_cert_path: <string> | default = ""]
     *
     *   # Path to private key file used by TLS.
     *   # CLI flag: -cassandra.tls-key-path
     *   [tls_key_path: <string> | default = ""]
     *
     *   # Enable password authentication when connecting to cassandra.
     *   # CLI flag: -cassandra.auth
     *   [auth: <boolean> | default = false]
     *
     *   # Username to use when connecting to cassandra.
     *   # CLI flag: -cassandra.username
     *   [username: <string> | default = ""]
     *
     *   # Password to use when connecting to cassandra.
     *   # CLI flag: -cassandra.password
     *   [password: <string> | default = ""]
     *
     *   # File containing password to use when connecting to cassandra.
     *   # CLI flag: -cassandra.password-file
     *   [password_file: <string> | default = ""]
     *
     *   # If set, when authenticating with cassandra a custom authenticator will be
     *   # expected during the handshake. This flag can be set multiple times.
     *   # CLI flag: -cassandra.custom-authenticator
     *   [custom_authenticators: <list of strings> | default = []]
     *
     *   # Timeout when connecting to cassandra.
     *   # CLI flag: -cassandra.timeout
     *   [timeout: <duration> | default = 2s]
     *
     *   # Initial connection timeout, used during initial dial to server.
     *   # CLI flag: -cassandra.connect-timeout
     *   [connect_timeout: <duration> | default = 5s]
     *
     *   # Interval to retry connecting to cassandra nodes marked as DOWN.
     *   # CLI flag: -cassandra.reconnent-interval
     *   [reconnect_interval: <duration> | default = 1s]
     *
     *   # Number of retries to perform on a request. Set to 0 to disable retries.
     *   # CLI flag: -cassandra.max-retries
     *   [max_retries: <int> | default = 0]
     *
     *   # Maximum time to wait before retrying a failed request.
     *   # CLI flag: -cassandra.retry-max-backoff
     *   [retry_max_backoff: <duration> | default = 10s]
     *
     *   # Minimum time to wait before retrying a failed request.
     *   # CLI flag: -cassandra.retry-min-backoff
     *   [retry_min_backoff: <duration> | default = 100ms]
     *
     *   # Limit number of concurrent queries to Cassandra. Set to 0 to disable the
     *   # limit.
     *   # CLI flag: -cassandra.query-concurrency
     *   [query_concurrency: <int> | default = 0]
     *
     *   # Number of TCP connections per host.
     *   # CLI flag: -cassandra.num-connections
     *   [num_connections: <int> | default = 2]
     *
     *   # Convict hosts of being down on failure.
     *   # CLI flag: -cassandra.convict-hosts-on-failure
     *   [convict_hosts_on_failure: <boolean> | default = true]
     *
     *   # Table options used to create index or chunk tables. This value is used as
     *   # plain text in the table `WITH` like this, "CREATE TABLE
     *   # <generated_by_cortex> (...) WITH <cassandra.table-options>". For details,
     *   # see https://cortexmetrics.io/docs/production/cassandra. By default it will
     *   # use the default table options of your Cassandra cluster.
     *   # CLI flag: -cassandra.table-options
     *   [table_options: <string> | default = ""]
     *
     * # Deprecated: Configures storing index in BoltDB. Required fields only required
     * # when boltdb is present in the configuration.
     * boltdb:
     *   # Location of BoltDB index files.
     *   # CLI flag: -boltdb.dir
     *   [directory: <string> | default = ""]
     *
     * # Configures storing the chunks on the local file system. Required fields only
     * # required when filesystem is present in the configuration.
     * [filesystem: <local_storage_config>]
     *
     * # The swift_storage_config block configures the connection to OpenStack Object
     * # Storage (Swift) object storage backend.
     * [swift: <swift_storage_config>]
     *
     * # Deprecated:
     * grpc_store:
     *   # Hostname or IP of the gRPC store instance.
     *   # CLI flag: -grpc-store.server-address
     *   [server_address: <string> | default = ""]
     *
     * hedging:
     *   # If set to a non-zero value a second request will be issued at the provided
     *   # duration. Default is 0 (disabled)
     *   # CLI flag: -store.hedge-requests-at
     *   [at: <duration> | default = 0s]
     *
     *   # The maximum of hedge requests allowed.
     *   # CLI flag: -store.hedge-requests-up-to
     *   [up_to: <int> | default = 2]
     *
     *   # The maximum of hedge requests allowed per seconds.
     *   # CLI flag: -store.hedge-max-per-second
     *   [max_per_second: <int> | default = 5]
     *
     * # Configures additional object stores for a given storage provider.
     * # Supported stores: aws, azure, bos, filesystem, gcs, swift.
     * # Example:
     * # ```yaml
     * #     storage_config:
     * #       named_stores:
     * #         aws:
     * #           store-1:
     * #             endpoint: s3://foo-bucket
     * #             region: us-west1
     * # ```
     * # Named store from this example can be used by setting object_store to store-1
     * # in period_config.
     * [named_stores: <named_stores_config>]
     *
     * # The cos_storage_config block configures the connection to IBM Cloud Object
     * # Storage (COS) backend.
     * [cos: <cos_storage_config>]
     *
     * # Cache validity for active index entries. Should be no higher than
     * # -ingester.max-chunk-idle.
     * # CLI flag: -store.index-cache-validity
     * [index_cache_validity: <duration> | default = 5m]
     *
     * congestion_control:
     *   # Use storage congestion control (default: disabled).
     *   # CLI flag: -store.congestion-control.enabled
     *   [enabled: <boolean> | default = false]
     *
     *   controller:
     *     # Congestion control strategy to use (default: none, options: 'aimd').
     *     # CLI flag: -store.congestion-control.strategy
     *     [strategy: <string> | default = ""]
     *
     *     aimd:
     *       # AIMD starting throughput window size: how many requests can be sent per
     *       # second (default: 2000).
     *       # CLI flag: -store.congestion-control.strategy.aimd.start
     *       [start: <int> | default = 2000]
     *
     *       # AIMD maximum throughput window size: upper limit of requests sent per
     *       # second (default: 10000).
     *       # CLI flag: -store.congestion-control.strategy.aimd.upper-bound
     *       [upper_bound: <int> | default = 10000]
     *
     *       # AIMD backoff factor when upstream service is throttled to decrease
     *       # number of requests sent per second (default: 0.5).
     *       # CLI flag: -store.congestion-control.strategy.aimd.backoff-factor
     *       [backoff_factor: <float> | default = 0.5]
     *
     *   retry:
     *     # Congestion control retry strategy to use (default: none, options:
     *     # 'limited').
     *     # CLI flag: -store.congestion-control.retry.strategy
     *     [strategy: <string> | default = ""]
     *
     *     # Maximum number of retries allowed.
     *     # CLI flag: -store.congestion-control.retry.strategy.limited.limit
     *     [limit: <int> | default = 2]
     *
     *   hedging:
     *     config:
     *       [at: <duration>]
     *
     *       [up_to: <int>]
     *
     *       [max_per_second: <int>]
     *
     *     # Congestion control hedge strategy to use (default: none, options:
     *     # 'limited').
     *     # CLI flag: -store.congestion-control.hedge.strategy
     *     [strategy: <string> | default = ""]
     *
     * # Experimental. Sets a constant prefix for all keys inserted into object
     * # storage. Example: loki/
     * # CLI flag: -store.object-prefix
     * [object_prefix: <string> | default = ""]
     *
     * # The cache_config block configures the cache backend for a specific Loki
     * # component.
     * # The CLI flags prefix for this block configuration is: store.index-cache-read
     * [index_queries_cache_config: <cache_config>]
     *
     * # Disable broad index queries which results in reduced cache usage and faster
     * # query performance at the expense of somewhat higher QPS on the index store.
     * # CLI flag: -store.disable-broad-index-queries
     * [disable_broad_index_queries: <boolean> | default = false]
     *
     * # Maximum number of parallel chunk reads.
     * # CLI flag: -store.max-parallel-get-chunk
     * [max_parallel_get_chunk: <int> | default = 150]
     *
     * # Enables the use of thanos-io/objstore clients for connecting to object
     * # storage. When set to true, the configuration inside
     * # `storage_config.object_store` or `common.storage.object_store` block takes
     * # effect.
     * # CLI flag: -use-thanos-objstore
     * [use_thanos_objstore: <boolean> | default = false]
     *
     * object_store:
     *   # The thanos_object_store_config block configures the connection to object
     *   # storage backend using thanos-io/objstore clients. This will become the
     *   # default way of configuring object store clients in future releases.
     *   # Currently this is opt-in and takes effect only when `-use-thanos-objstore`
     *   # is set to true.
     *   # The CLI flags prefix for this block configuration is: object-store
     *   [<thanos_object_store_config>]
     *
     *   named_stores:
     *     [azure: <map of string to NamedAzureStorageConfig>]
     *
     *     [filesystem: <map of string to NamedFilesystemStorageConfig>]
     *
     *     [gcs: <map of string to NamedGCSStorageConfig>]
     *
     *     [s3: <map of string to NamedS3StorageConfig>]
     *
     *     [swift: <map of string to NamedSwiftStorageConfig>]
     *
     * # The maximum number of chunks to fetch per batch.
     * # CLI flag: -store.max-chunk-batch-size
     * [max_chunk_batch_size: <int> | default = 50]
     *
     * # Configures storing index in an Object Store
     * # (GCS/S3/Azure/Swift/COS/Filesystem) in the form of boltdb files. Required
     * # fields only required when boltdb-shipper is defined in config.
     * boltdb_shipper:
     *   # Directory where ingesters would write index files which would then be
     *   # uploaded by shipper to configured storage
     *   # CLI flag: -boltdb.shipper.active-index-directory
     *   [active_index_directory: <string> | default = ""]
     *
     *   # Cache location for restoring index files from storage for queries
     *   # CLI flag: -boltdb.shipper.cache-location
     *   [cache_location: <string> | default = ""]
     *
     *   # TTL for index files restored in cache for queries
     *   # CLI flag: -boltdb.shipper.cache-ttl
     *   [cache_ttl: <duration> | default = 24h]
     *
     *   # Resync downloaded files with the storage
     *   # CLI flag: -boltdb.shipper.resync-interval
     *   [resync_interval: <duration> | default = 5m]
     *
     *   # Number of days of common index to be kept downloaded for queries. For per
     *   # tenant index query readiness, use limits overrides config.
     *   # CLI flag: -boltdb.shipper.query-ready-num-days
     *   [query_ready_num_days: <int> | default = 0]
     *
     *   index_gateway_client:
     *     # The grpc_client block configures the gRPC client used to communicate
     *     # between a client and server component in Loki.
     *     # The CLI flags prefix for this block configuration is:
     *     # boltdb.shipper.index-gateway-client.grpc
     *     [grpc_client_config: <grpc_client>]
     *
     *     # Hostname or IP of the Index Gateway gRPC server running in simple mode.
     *     # Can also be prefixed with dns+, dnssrv+, or dnssrvnoa+ to resolve a DNS A
     *     # record with multiple IP's, a DNS SRV record with a followup A record
     *     # lookup, or a DNS SRV record without a followup A record lookup,
     *     # respectively.
     *     # CLI flag: -boltdb.shipper.index-gateway-client.server-address
     *     [server_address: <string> | default = ""]
     *
     *     # Whether requests sent to the gateway should be logged or not.
     *     # CLI flag: -boltdb.shipper.index-gateway-client.log-gateway-requests
     *     [log_gateway_requests: <boolean> | default = false]
     *
     *   [ingestername: <string> | default = ""]
     *
     *   [mode: <string> | default = ""]
     *
     *   [ingesterdbretainperiod: <duration>]
     *
     *   # Build per tenant index files
     *   # CLI flag: -boltdb.shipper.build-per-tenant-index
     *   [build_per_tenant_index: <boolean> | default = false]
     *
     * # Configures storing index in an Object Store
     * # (GCS/S3/Azure/Swift/COS/Filesystem) in a prometheus TSDB-like format. Required
     * # fields only required when TSDB is defined in config.
     * tsdb_shipper:
     *   # Directory where ingesters would write index files which would then be
     *   # uploaded by shipper to configured storage
     *   # CLI flag: -tsdb.shipper.active-index-directory
     *   [active_index_directory: <string> | default = ""]
     *
     *   # Cache location for restoring index files from storage for queries
     *   # CLI flag: -tsdb.shipper.cache-location
     *   [cache_location: <string> | default = ""]
     *
     *   # TTL for index files restored in cache for queries
     *   # CLI flag: -tsdb.shipper.cache-ttl
     *   [cache_ttl: <duration> | default = 24h]
     *
     *   # Resync downloaded files with the storage
     *   # CLI flag: -tsdb.shipper.resync-interval
     *   [resync_interval: <duration> | default = 5m]
     *
     *   # Number of days of common index to be kept downloaded for queries. For per
     *   # tenant index query readiness, use limits overrides config.
     *   # CLI flag: -tsdb.shipper.query-ready-num-days
     *   [query_ready_num_days: <int> | default = 0]
     *
     *   index_gateway_client:
     *     # The grpc_client block configures the gRPC client used to communicate
     *     # between a client and server component in Loki.
     *     # The CLI flags prefix for this block configuration is:
     *     # tsdb.shipper.index-gateway-client.grpc
     *     [grpc_client_config: <grpc_client>]
     *
     *     # Hostname or IP of the Index Gateway gRPC server running in simple mode.
     *     # Can also be prefixed with dns+, dnssrv+, or dnssrvnoa+ to resolve a DNS A
     *     # record with multiple IP's, a DNS SRV record with a followup A record
     *     # lookup, or a DNS SRV record without a followup A record lookup,
     *     # respectively.
     *     # CLI flag: -tsdb.shipper.index-gateway-client.server-address
     *     [server_address: <string> | default = ""]
     *
     *     # Whether requests sent to the gateway should be logged or not.
     *     # CLI flag: -tsdb.shipper.index-gateway-client.log-gateway-requests
     *     [log_gateway_requests: <boolean> | default = false]
     *
     *   [ingestername: <string> | default = ""]
     *
     *   [mode: <string> | default = ""]
     *
     *   [ingesterdbretainperiod: <duration>]
     *
     * # Experimental: Configures the bloom shipper component, which contains the store
     * # abstraction to fetch bloom filters from and put them to object storage.
     * bloom_shipper:
     *   # Working directory to store downloaded bloom blocks. Supports multiple
     *   # directories, separated by comma.
     *   # CLI flag: -bloom.shipper.working-directory
     *   [working_directory: <string> | default = "/data/blooms"]
     *
     *   # Maximum size of bloom pages that should be queried. Larger pages than this
     *   # limit are skipped when querying blooms to limit memory usage.
     *   # CLI flag: -bloom.max-query-page-size
     *   [max_query_page_size: <int> | default = 64MiB]
     *
     *   # The amount of maximum concurrent bloom blocks downloads. Usually set to 2x
     *   # number of CPU cores.
     *   # CLI flag: -bloom.download-parallelism
     *   [download_parallelism: <int> | default = 8]
     *
     *   blocks_cache:
     *     # Cache for bloom blocks. Soft limit of the cache in bytes. Exceeding this
     *     # limit will trigger evictions of least recently used items in the
     *     # background.
     *     # CLI flag: -bloom.blocks-cache.soft-limit
     *     [soft_limit: <int> | default = 32GiB]
     *
     *     # Cache for bloom blocks. Hard limit of the cache in bytes. Exceeding this
     *     # limit will block execution until soft limit is deceeded.
     *     # CLI flag: -bloom.blocks-cache.hard-limit
     *     [hard_limit: <int> | default = 64GiB]
     *
     *     # Cache for bloom blocks. The time to live for items in the cache before
     *     # they get purged.
     *     # CLI flag: -bloom.blocks-cache.ttl
     *     [ttl: <duration> | default = 24h]
     *
     *   # The cache_config block configures the cache backend for a specific Loki
     *   # component.
     *   # The CLI flags prefix for this block configuration is: bloom.metas-cache
     *   [metas_cache: <cache_config>]
     *
     *   metas_lru_cache:
     *     # In-memory LRU cache for bloom metas. Whether embedded cache is enabled.
     *     # CLI flag: -bloom.metas-lru-cache.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # In-memory LRU cache for bloom metas. Maximum memory size of the cache in
     *     # MB.
     *     # CLI flag: -bloom.metas-lru-cache.max-size-mb
     *     [max_size_mb: <int> | default = 100]
     *
     *     # In-memory LRU cache for bloom metas. Maximum number of entries in the
     *     # cache.
     *     # CLI flag: -bloom.metas-lru-cache.max-size-items
     *     [max_size_items: <int> | default = 0]
     *
     *     # In-memory LRU cache for bloom metas. The time to live for items in the
     *     # cache before they get purged.
     *     # CLI flag: -bloom.metas-lru-cache.ttl
     *     [ttl: <duration> | default = 1h]
     */
)