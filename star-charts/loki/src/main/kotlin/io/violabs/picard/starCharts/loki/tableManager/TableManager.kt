package io.violabs.picard.starCharts.loki.tableManager

import io.violabs.picard.starCharts.loki.Duration

class TableManager(
    /**
     * # If true, disable all changes to DB capacity
     * # CLI flag: -table-manager.throughput-updates-disabled
     * [throughput_updates_disabled: <boolean> | default = false]
     */
    val throughputUpdatesDisabled: Boolean = false,
    /**
     * # If true, enables retention deletes of DB tables
     * # CLI flag: -table-manager.retention-deletes-enabled
     * [retention_deletes_enabled: <boolean> | default = false]
     */
    val retentionDeletesEnabled: Boolean = false,
    /**
     * # Tables older than this retention period are deleted. Must be either 0
     * # (disabled) or a multiple of 24h. When enabled, be aware this setting is
     * # destructive to data!
     * # CLI flag: -table-manager.retention-period
     * [retention_period: <duration> | default = 0s]
     */
    val retentionPeriod: Duration? = null,
    /**
     * # How frequently to poll backend to learn our capacity.
     * # CLI flag: -table-manager.poll-interval
     * [poll_interval: <duration> | default = 2m]
     */
    val pollInterval: Duration? = null,
    /**
     * # Periodic tables grace period (duration which table will be created/deleted
     * # before/after it's needed).
     * # CLI flag: -table-manager.periodic-table.grace-period
     * [creation_grace_period: <duration> | default = 10m]
     */
    val creationGracePeriod: Duration? = null,
    val indexTablesProvisioning: TablesProvisioning? = null,
    val chunkTablesProvisioning: TablesProvisioning? = null
) {
}