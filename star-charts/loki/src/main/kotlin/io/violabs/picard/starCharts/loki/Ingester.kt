package io.violabs.picard.starCharts.loki

class Ingester(
    /**
     * # Configures how the lifecycle of the ingester will operate and where it will
     * # register for discovery.
     * lifecycler:
     *   ring:
     *     kvstore:
     *       # Backend storage to use for the ring. Supported values are: consul, etcd,
     *       # inmemory, memberlist, multi.
     *       # CLI flag: -ring.store
     *       [store: <string> | default = "consul"]
     *
     *       # The prefix for the keys in the store. Should end with a /.
     *       # CLI flag: -ring.prefix
     *       [prefix: <string> | default = "collectors/"]
     *
     *       # Configuration for a Consul client. Only applies if the selected kvstore
     *       # is consul.
     *       [consul: <consul>]
     *
     *       # Configuration for an ETCD v3 client. Only applies if the selected
     *       # kvstore is etcd.
     *       [etcd: <etcd>]
     *
     *       multi:
     *         # Primary backend storage used by multi-client.
     *         # CLI flag: -multi.primary
     *         [primary: <string> | default = ""]
     *
     *         # Secondary backend storage used by multi-client.
     *         # CLI flag: -multi.secondary
     *         [secondary: <string> | default = ""]
     *
     *         # Mirror writes to secondary store.
     *         # CLI flag: -multi.mirror-enabled
     *         [mirror_enabled: <boolean> | default = false]
     *
     *         # Timeout for storing value to secondary store.
     *         # CLI flag: -multi.mirror-timeout
     *         [mirror_timeout: <duration> | default = 2s]
     *
     *     # The heartbeat timeout after which ingesters are skipped for reads/writes.
     *     # 0 = never (timeout disabled).
     *     # CLI flag: -ring.heartbeat-timeout
     *     [heartbeat_timeout: <duration> | default = 1m]
     *
     *     # The number of ingesters to write to and read from.
     *     # CLI flag: -distributor.replication-factor
     *     [replication_factor: <int> | default = 3]
     *
     *     # True to enable the zone-awareness and replicate ingested samples across
     *     # different availability zones.
     *     # CLI flag: -distributor.zone-awareness-enabled
     *     [zone_awareness_enabled: <boolean> | default = false]
     *
     *     # Comma-separated list of zones to exclude from the ring. Instances in
     *     # excluded zones will be filtered out from the ring.
     *     # CLI flag: -distributor.excluded-zones
     *     [excluded_zones: <string> | default = ""]
     *
     *   # Number of tokens for each ingester.
     *   # CLI flag: -ingester.num-tokens
     *   [num_tokens: <int> | default = 128]
     *
     *   # Period at which to heartbeat to consul. 0 = disabled.
     *   # CLI flag: -ingester.heartbeat-period
     *   [heartbeat_period: <duration> | default = 5s]
     *
     *   # Heartbeat timeout after which instance is assumed to be unhealthy. 0 =
     *   # disabled.
     *   # CLI flag: -ingester.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # Observe tokens after generating to resolve collisions. Useful when using
     *   # gossiping ring.
     *   # CLI flag: -ingester.observe-period
     *   [observe_period: <duration> | default = 0s]
     *
     *   # Period to wait for a claim from another member; will join automatically
     *   # after this.
     *   # CLI flag: -ingester.join-after
     *   [join_after: <duration> | default = 0s]
     *
     *   # Minimum duration to wait after the internal readiness checks have passed but
     *   # before succeeding the readiness endpoint. This is used to slowdown
     *   # deployment controllers (eg. Kubernetes) after an instance is ready and
     *   # before they proceed with a rolling update, to give the rest of the cluster
     *   # instances enough time to receive ring updates.
     *   # CLI flag: -ingester.min-ready-duration
     *   [min_ready_duration: <duration> | default = 15s]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -ingester.lifecycler.interface
     *   [interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # Enable IPv6 support. Required to make use of IP addresses from IPv6
     *   # interfaces.
     *   # CLI flag: -ingester.enable-inet6
     *   [enable_inet6: <boolean> | default = false]
     *
     *   # Duration to sleep for before exiting, to ensure metrics are scraped.
     *   # CLI flag: -ingester.final-sleep
     *   [final_sleep: <duration> | default = 0s]
     *
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -ingester.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     *
     *   # The availability zone where this instance is running.
     *   # CLI flag: -ingester.availability-zone
     *   [availability_zone: <string> | default = ""]
     *
     *   # Unregister from the ring upon clean shutdown. It can be useful to disable
     *   # for rolling restarts with consistent naming in conjunction with
     *   # -distributor.extend-writes=false.
     *   # CLI flag: -ingester.unregister-on-shutdown
     *   [unregister_on_shutdown: <boolean> | default = true]
     *
     *   # When enabled the readiness probe succeeds only after all instances are
     *   # ACTIVE and healthy in the ring, otherwise only the instance itself is
     *   # checked. This option should be disabled if in your cluster multiple
     *   # instances can be rolled out simultaneously, otherwise rolling updates may be
     *   # slowed down.
     *   # CLI flag: -ingester.readiness-check-ring-health
     *   [readiness_check_ring_health: <boolean> | default = true]
     *
     *   # IP address to advertise in the ring.
     *   # CLI flag: -ingester.lifecycler.addr
     *   [address: <string> | default = ""]
     *
     *   # port to advertise in consul (defaults to server.grpc-listen-port).
     *   # CLI flag: -ingester.lifecycler.port
     *   [port: <int> | default = 0]
     *
     *   # ID to register in the ring.
     *   # CLI flag: -ingester.lifecycler.ID
     *   [id: <string> | default = "<hostname>"]
     *
     * # How many flushes can happen concurrently from each stream.
     * # CLI flag: -ingester.concurrent-flushes
     * [concurrent_flushes: <int> | default = 32]
     *
     * # How often should the ingester see if there are any blocks to flush. The first
     * # flush check is delayed by a random time up to 0.8x the flush check period.
     * # Additionally, there is +/- 1% jitter added to the interval.
     * # CLI flag: -ingester.flush-check-period
     * [flush_check_period: <duration> | default = 30s]
     *
     * flush_op_backoff:
     *   # Minimum backoff period when a flush fails. Each concurrent flush has its own
     *   # backoff, see `ingester.concurrent-flushes`.
     *   # CLI flag: -ingester.flush-op-backoff-min-period
     *   [min_period: <duration> | default = 10s]
     *
     *   # Maximum backoff period when a flush fails. Each concurrent flush has its own
     *   # backoff, see `ingester.concurrent-flushes`.
     *   # CLI flag: -ingester.flush-op-backoff-max-period
     *   [max_period: <duration> | default = 1m]
     *
     *   # Maximum retries for failed flushes.
     *   # CLI flag: -ingester.flush-op-backoff-retries
     *   [max_retries: <int> | default = 10]
     *
     * # The timeout for an individual flush. Will be retried up to
     * # `flush-op-backoff-retries` times.
     * # CLI flag: -ingester.flush-op-timeout
     * [flush_op_timeout: <duration> | default = 10m]
     *
     * # How long chunks should be retained in-memory after they've been flushed.
     * # CLI flag: -ingester.chunks-retain-period
     * [chunk_retain_period: <duration> | default = 0s]
     *
     * # How long chunks should sit in-memory with no updates before being flushed if
     * # they don't hit the max block size. This means that half-empty chunks will
     * # still be flushed after a certain period as long as they receive no further
     * # activity.
     * # CLI flag: -ingester.chunks-idle-period
     * [chunk_idle_period: <duration> | default = 30m]
     *
     * # The targeted _uncompressed_ size in bytes of a chunk block When this threshold
     * # is exceeded the head block will be cut and compressed inside the chunk.
     * # CLI flag: -ingester.chunks-block-size
     * [chunk_block_size: <int> | default = 262144]
     *
     * # A target _compressed_ size in bytes for chunks. This is a desired size not an
     * # exact size, chunks may be slightly bigger or significantly smaller if they get
     * # flushed for other reasons (e.g. chunk_idle_period). A value of 0 creates
     * # chunks with a fixed 10 blocks, a non zero value will create chunks with a
     * # variable number of blocks to meet the target size.
     * # CLI flag: -ingester.chunk-target-size
     * [chunk_target_size: <int> | default = 1572864]
     *
     * # The algorithm to use for compressing chunk. (none, gzip, lz4-64k, snappy,
     * # lz4-256k, lz4-1M, lz4, flate, zstd)
     * # CLI flag: -ingester.chunk-encoding
     * [chunk_encoding: <string> | default = "gzip"]
     *
     * # The maximum duration of a timeseries chunk in memory. If a timeseries runs for
     * # longer than this, the current chunk will be flushed to the store and a new
     * # chunk created.
     * # CLI flag: -ingester.max-chunk-age
     * [max_chunk_age: <duration> | default = 2h]
     *
     * # Forget about ingesters having heartbeat timestamps older than
     * # `ring.kvstore.heartbeat_timeout`. This is equivalent to clicking on the
     * # `/ring` `forget` button in the UI: the ingester is removed from the ring. This
     * # is a useful setting when you are sure that an unhealthy node won't return. An
     * # example is when not using stateful sets or the equivalent. Use
     * # `memberlist.rejoin_interval` > 0 to handle network partition cases when using
     * # a memberlist.
     * # CLI flag: -ingester.autoforget-unhealthy
     * [autoforget_unhealthy: <boolean> | default = false]
     *
     * # Parameters used to synchronize ingesters to cut chunks at the same moment.
     * # Sync period is used to roll over incoming entry to a new chunk. If chunk's
     * # utilization isn't high enough (eg. less than 50% when sync_min_utilization is
     * # set to 0.5), then this chunk rollover doesn't happen.
     * # CLI flag: -ingester.sync-period
     * [sync_period: <duration> | default = 1h]
     *
     * # Minimum utilization of chunk when doing synchronization.
     * # CLI flag: -ingester.sync-min-utilization
     * [sync_min_utilization: <float> | default = 0.1]
     *
     * # The maximum number of errors a stream will report to the user when a push
     * # fails. 0 to make unlimited.
     * # CLI flag: -ingester.max-ignored-stream-errors
     * [max_returned_stream_errors: <int> | default = 10]
     *
     * # How far back should an ingester be allowed to query the store for data, for
     * # use only with boltdb-shipper/tsdb index and filesystem object store. -1 for
     * # infinite.
     * # CLI flag: -ingester.query-store-max-look-back-period
     * [query_store_max_look_back_period: <duration> | default = 0s]
     *
     * # The ingester WAL (Write Ahead Log) records incoming logs and stores them on
     * # the local file systems in order to guarantee persistence of acknowledged data
     * # in the event of a process crash.
     * wal:
     *   # Enable writing of ingested data into WAL.
     *   # CLI flag: -ingester.wal-enabled
     *   [enabled: <boolean> | default = true]
     *
     *   # Directory where the WAL data is stored and/or recovered from.
     *   # CLI flag: -ingester.wal-dir
     *   [dir: <string> | default = "wal"]
     *
     *   # Interval at which checkpoints should be created.
     *   # CLI flag: -ingester.checkpoint-duration
     *   [checkpoint_duration: <duration> | default = 5m]
     *
     *   # When WAL is enabled, should chunks be flushed to long-term storage on
     *   # shutdown.
     *   # CLI flag: -ingester.flush-on-shutdown
     *   [flush_on_shutdown: <boolean> | default = false]
     *
     *   # Maximum memory size the WAL may use during replay. After hitting this, it
     *   # will flush data to storage before continuing. A unit suffix (KB, MB, GB) may
     *   # be applied.
     *   # CLI flag: -ingester.wal-replay-memory-ceiling
     *   [replay_memory_ceiling: <int> | default = 4GB]
     *
     * # Shard factor used in the ingesters for the in process reverse index. This MUST
     * # be evenly divisible by ALL schema shard factors or Loki will not start.
     * # CLI flag: -ingester.index-shards
     * [index_shards: <int> | default = 32]
     *
     * # Maximum number of dropped streams to keep in memory during tailing.
     * # CLI flag: -ingester.tailer.max-dropped-streams
     * [max_dropped_streams: <int> | default = 10]
     *
     * # Path where the shutdown marker file is stored. If not set and
     * # common.path_prefix is set then common.path_prefix will be used.
     * # CLI flag: -ingester.shutdown-marker-path
     * [shutdown_marker_path: <string> | default = ""]
     *
     * # Interval at which the ingester ownedStreamService checks for changes in the
     * # ring to recalculate owned streams.
     * # CLI flag: -ingester.owned-streams-check-interval
     * [owned_streams_check_interval: <duration> | default = 30s]
     *
     * kafka_ingestion:
     *   # Whether the kafka ingester is enabled.
     *   # CLI flag: -ingester.kafka-ingestion-enabled
     *   [enabled: <boolean> | default = false]
     *
     *   partition_ring:
     *     # The key-value store used to share the hash ring across multiple instances.
     *     # This option needs be set on ingesters, distributors, queriers, and rulers
     *     # when running in microservices mode.
     *     kvstore:
     *       # Backend storage to use for the ring. Supported values are: consul, etcd,
     *       # inmemory, memberlist, multi.
     *       # CLI flag: -ingester.partition-ring.store
     *       [store: <string> | default = "memberlist"]
     *
     *       # The prefix for the keys in the store. Should end with a /.
     *       # CLI flag: -ingester.partition-ring.prefix
     *       [prefix: <string> | default = "collectors/"]
     *
     *       # Configuration for a Consul client. Only applies if the selected kvstore
     *       # is consul.
     *       # The CLI flags prefix for this block configuration is:
     *       # ingester.partition-ring
     *       [consul: <consul>]
     *
     *       # Configuration for an ETCD v3 client. Only applies if the selected
     *       # kvstore is etcd.
     *       # The CLI flags prefix for this block configuration is:
     *       # ingester.partition-ring
     *       [etcd: <etcd>]
     *
     *       multi:
     *         # Primary backend storage used by multi-client.
     *         # CLI flag: -ingester.partition-ring.multi.primary
     *         [primary: <string> | default = ""]
     *
     *         # Secondary backend storage used by multi-client.
     *         # CLI flag: -ingester.partition-ring.multi.secondary
     *         [secondary: <string> | default = ""]
     *
     *         # Mirror writes to secondary store.
     *         # CLI flag: -ingester.partition-ring.multi.mirror-enabled
     *         [mirror_enabled: <boolean> | default = false]
     *
     *         # Timeout for storing value to secondary store.
     *         # CLI flag: -ingester.partition-ring.multi.mirror-timeout
     *         [mirror_timeout: <duration> | default = 2s]
     *
     *     # Minimum number of owners to wait before a PENDING partition gets switched
     *     # to ACTIVE.
     *     # CLI flag: -ingester.partition-ring.min-partition-owners-count
     *     [min_partition_owners_count: <int> | default = 1]
     *
     *     # How long the minimum number of owners are enforced before a PENDING
     *     # partition gets switched to ACTIVE.
     *     # CLI flag: -ingester.partition-ring.min-partition-owners-duration
     *     [min_partition_owners_duration: <duration> | default = 10s]
     *
     *     # How long to wait before an INACTIVE partition is eligible for deletion.
     *     # The partition is deleted only if it has been in INACTIVE state for at
     *     # least the configured duration and it has no owners registered. A value of
     *     # 0 disables partitions deletion.
     *     # CLI flag: -ingester.partition-ring.delete-inactive-partition-after
     *     [delete_inactive_partition_after: <duration> | default = 13h]
     */
) {
}