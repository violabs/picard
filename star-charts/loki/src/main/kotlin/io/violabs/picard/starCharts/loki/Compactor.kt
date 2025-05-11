package io.violabs.picard.starCharts.loki

class Compactor(
    /**
     * # Directory where files can be downloaded for compaction.
     * # CLI flag: -compactor.working-directory
     * [working_directory: <string> | default = "/var/loki/compactor"]
     *
     * # Interval at which to re-run the compaction operation.
     * # CLI flag: -compactor.compaction-interval
     * [compaction_interval: <duration> | default = 10m]
     *
     * # Interval at which to apply/enforce retention. 0 means run at same interval as
     * # compaction. If non-zero, it should always be a multiple of compaction
     * # interval.
     * # CLI flag: -compactor.apply-retention-interval
     * [apply_retention_interval: <duration> | default = 0s]
     *
     * # Activate custom (per-stream,per-tenant) retention.
     * # CLI flag: -compactor.retention-enabled
     * [retention_enabled: <boolean> | default = false]
     *
     * # Delay after which chunks will be fully deleted during retention.
     * # CLI flag: -compactor.retention-delete-delay
     * [retention_delete_delay: <duration> | default = 2h]
     *
     * # The total amount of worker to use to delete chunks.
     * # CLI flag: -compactor.retention-delete-worker-count
     * [retention_delete_worker_count: <int> | default = 150]
     *
     * # The maximum amount of time to spend running retention and deletion on any
     * # given table in the index.
     * # CLI flag: -compactor.retention-table-timeout
     * [retention_table_timeout: <duration> | default = 0s]
     *
     * retention_backoff_config:
     *   # Minimum delay when backing off.
     *   # CLI flag: -compactor.retention-backoff-config.backoff-min-period
     *   [min_period: <duration> | default = 100ms]
     *
     *   # Maximum delay when backing off.
     *   # CLI flag: -compactor.retention-backoff-config.backoff-max-period
     *   [max_period: <duration> | default = 10s]
     *
     *   # Number of times to backoff and retry before failing.
     *   # CLI flag: -compactor.retention-backoff-config.backoff-retries
     *   [max_retries: <int> | default = 10]
     *
     * # Store used for managing delete requests.
     * # CLI flag: -compactor.delete-request-store
     * [delete_request_store: <string> | default = ""]
     *
     * # Path prefix for storing delete requests.
     * # CLI flag: -compactor.delete-request-store.key-prefix
     * [delete_request_store_key_prefix: <string> | default = "index/"]
     *
     * # Type of DB to use for storing delete requests. Supported types: boltdb, sqlite
     * # CLI flag: -compactor.delete-request-store.db-type
     * [delete_request_store_db_type: <string> | default = "boltdb"]
     *
     * # Type of DB to use as backup for storing delete requests. Backup DB should
     * # ideally be used while migrating from one DB type to another. Supported
     * # type(s): boltdb
     * # CLI flag: -compactor.delete-request-store.backup-db-type
     * [backup_delete_request_store_db_type: <string> | default = ""]
     *
     * # The max number of delete requests to run per compaction cycle.
     * # CLI flag: -compactor.delete-batch-size
     * [delete_batch_size: <int> | default = 70]
     *
     * # Allow cancellation of delete request until duration after they are created.
     * # Data would be deleted only after delete requests have been older than this
     * # duration. Ideally this should be set to at least 24h.
     * # CLI flag: -compactor.delete-request-cancel-period
     * [delete_request_cancel_period: <duration> | default = 24h]
     *
     * # Constrain the size of any single delete request with line filters. When a
     * # delete request > delete_max_interval is input, the request is sharded into
     * # smaller requests of no more than delete_max_interval
     * # CLI flag: -compactor.delete-max-interval
     * [delete_max_interval: <duration> | default = 24h]
     *
     * # Maximum number of tables to compact in parallel. While increasing this value,
     * # please make sure compactor has enough disk space allocated to be able to store
     * # and compact as many tables.
     * # CLI flag: -compactor.max-compaction-parallelism
     * [max_compaction_parallelism: <int> | default = 1]
     *
     * # Number of upload/remove operations to execute in parallel when finalizing a
     * # compaction. NOTE: This setting is per compaction operation, which can be
     * # executed in parallel. The upper bound on the number of concurrent uploads is
     * # upload_parallelism * max_compaction_parallelism.
     * # CLI flag: -compactor.upload-parallelism
     * [upload_parallelism: <int> | default = 10]
     *
     * # The hash ring configuration used by compactors to elect a single instance for
     * # running compactions. The CLI flags prefix for this block config is:
     * # compactor.ring
     * compactor_ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -compactor.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -compactor.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: compactor.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: compactor.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -compactor.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -compactor.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -compactor.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -compactor.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -compactor.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 15s]
     *
     *   # The heartbeat timeout after which compactors are considered unhealthy within
     *   # the ring. 0 = never (timeout disabled).
     *   # CLI flag: -compactor.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # File path where tokens are stored. If empty, tokens are not stored at
     *   # shutdown and restored at startup.
     *   # CLI flag: -compactor.ring.tokens-file-path
     *   [tokens_file_path: <string> | default = ""]
     *
     *   # True to enable zone-awareness and replicate blocks across different
     *   # availability zones.
     *   # CLI flag: -compactor.ring.zone-awareness-enabled
     *   [zone_awareness_enabled: <boolean> | default = false]
     *
     *   # Instance ID to register in the ring.
     *   # CLI flag: -compactor.ring.instance-id
     *   [instance_id: <string> | default = "<hostname>"]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -compactor.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # Port to advertise in the ring (defaults to server.grpc-listen-port).
     *   # CLI flag: -compactor.ring.instance-port
     *   [instance_port: <int> | default = 0]
     *
     *   # IP address to advertise in the ring.
     *   # CLI flag: -compactor.ring.instance-addr
     *   [instance_addr: <string> | default = ""]
     *
     *   # The availability zone where this instance is running. Required if
     *   # zone-awareness is enabled.
     *   # CLI flag: -compactor.ring.instance-availability-zone
     *   [instance_availability_zone: <string> | default = ""]
     *
     *   # Enable using a IPv6 instance address.
     *   # CLI flag: -compactor.ring.instance-enable-ipv6
     *   [instance_enable_ipv6: <boolean> | default = false]
     *
     * # Number of tables that compactor will try to compact. Newer tables are chosen
     * # when this is less than the number of tables available.
     * # CLI flag: -compactor.tables-to-compact
     * [tables_to_compact: <int> | default = 0]
     *
     * # Do not compact N latest tables. Together with -compactor.run-once and
     * # -compactor.tables-to-compact, this is useful when clearing compactor backlogs.
     * # CLI flag: -compactor.skip-latest-n-tables
     * [skip_latest_n_tables: <int> | default = 0]
     */
) {
}