package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDsl
class Compactor(
    /**
     * # Directory where files can be downloaded for compaction.
     * # CLI flag: -compactor.working-directory
     * [working_directory: <string> | default = "/var/loki/compactor"]
     */
    val workingDirectory: String? = null,
    /**
     * # Interval at which to re-run the compaction operation.
     * # CLI flag: -compactor.compaction-interval
     * [compaction_interval: <duration> | default = 10m]
     */
    val compactionInterval: Duration? = null,
    /**
     * # Interval at which to apply/enforce retention. 0 means run at same interval as
     * # compaction. If non-zero, it should always be a multiple of compaction
     * # interval.
     * # CLI flag: -compactor.apply-retention-interval
     * [apply_retention_interval: <duration> | default = 0s]
     */
    val applyRetentionInterval: Duration? = null,
    /**
     * # Activate custom (per-stream,per-tenant) retention.
     * # CLI flag: -compactor.retention-enabled
     * [retention_enabled: <boolean> | default = false]
     */
    val retentionEnabled: Boolean? = null,
    /**
     * # Delay after which chunks will be fully deleted during retention.
     * # CLI flag: -compactor.retention-delete-delay
     * [retention_delete_delay: <duration> | default = 2h]
     */
    val retentionDeleteDelay: Duration? = null,
    /**
     * # The total amount of worker to use to delete chunks.
     * # CLI flag: -compactor.retention-delete-worker-count
     * [retention_delete_worker_count: <int> | default = 150]
     */
    val retentionDeleteWorkerCount: Int? = null,
    /**
     * # The maximum amount of time to spend running retention and deletion on any
     * # given table in the index.
     * # CLI flag: -compactor.retention-table-timeout
     * [retention_table_timeout: <duration> | default = 0s]
     */
    val retentionTableTimeout: Duration? = null,
    val retentionBackoffConfig: BackoffConfig? = null,
    /**
     * # Store used for managing delete requests.
     * # CLI flag: -compactor.delete-request-store
     * [delete_request_store: <string> | default = ""]
     */
    val deleteRequestStore: String? = null,
    /**
     * # Path prefix for storing delete requests.
     * # CLI flag: -compactor.delete-request-store.key-prefix
     * [delete_request_store_key_prefix: <string> | default = "index/"]
     */
    val deleteRequestStoreKeyPrefix: String? = null,
    /**
     * # Type of DB to use for storing delete requests. Supported types: boltdb, sqlite
     * # CLI flag: -compactor.delete-request-store.db-type
     * [delete_request_store_db_type: <string> | default = "boltdb"]
     */
    val deleteRequestStoreDBType: String? = null,
    /**
     * # Type of DB to use as backup for storing delete requests. Backup DB should
     * # ideally be used while migrating from one DB type to another. Supported
     * # type(s): boltdb
     * # CLI flag: -compactor.delete-request-store.backup-db-type
     * [backup_delete_request_store_db_type: <string> | default = ""]
     */
    val backupDeleteRequestStoreDBType: String? = null,
    /**
     * # The max number of delete requests to run per compaction cycle.
     * # CLI flag: -compactor.delete-batch-size
     * [delete_batch_size: <int> | default = 70]
     */
    val deleteBatchSize: Int? = null,
    /**
     * # Allow cancellation of delete request until duration after they are created.
     * # Data would be deleted only after delete requests have been older than this
     * # duration. Ideally this should be set to at least 24h.
     * # CLI flag: -compactor.delete-request-cancel-period
     * [delete_request_cancel_period: <duration> | default = 24h]
     */
    val deleteRequestCancelPeriod: Duration? = null,
    /**
     * # Constrain the size of any single delete request with line filters. When a
     * # delete request > delete_max_interval is input, the request is sharded into
     * # smaller requests of no more than delete_max_interval
     * # CLI flag: -compactor.delete-max-interval
     * [delete_max_interval: <duration> | default = 24h]
     */
    val deleteMaxInterval: Duration? = null,
    /**
     * # Maximum number of tables to compact in parallel. While increasing this value,
     * # please make sure compactor has enough disk space allocated to be able to store
     * # and compact as many tables.
     * # CLI flag: -compactor.max-compaction-parallelism
     * [max_compaction_parallelism: <int> | default = 1]
     */
    val maxCompactionParallelism: Int? = null,
    /**
     * # Number of upload/remove operations to execute in parallel when finalizing a
     * # compaction. NOTE: This setting is per compaction operation, which can be
     * # executed in parallel. The upper bound on the number of concurrent uploads is
     * # upload_parallelism * max_compaction_parallelism.
     * # CLI flag: -compactor.upload-parallelism
     * [upload_parallelism: <int> | default = 10]
     */
    val uploadParallelism: Int? = null,
    /**
     * # The hash ring configuration used by compactors to elect a single instance for
     * # running compactions. The CLI flags prefix for this block config is:
     * # compactor.ring
     */
    val compactorRing: Ring? = null,
    /**
     * # Number of tables that compactor will try to compact. Newer tables are chosen
     * # when this is less than the number of tables available.
     * # CLI flag: -compactor.tables-to-compact
     * [tables_to_compact: <int> | default = 0]
     */
    val tablesToCompact: Int? = null,
    /**
     * # Do not compact N latest tables. Together with -compactor.run-once and
     * # -compactor.tables-to-compact, this is useful when clearing compactor backlogs.
     * # CLI flag: -compactor.skip-latest-n-tables
     * [skip_latest_n_tables: <int> | default = 0]
     */
    val skipLatestNTables: Int? = null
)