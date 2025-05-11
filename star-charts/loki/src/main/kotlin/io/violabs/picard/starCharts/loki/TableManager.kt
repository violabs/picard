package io.violabs.picard.starCharts.loki

class TableManager(
    /**
     * # If true, disable all changes to DB capacity
     * # CLI flag: -table-manager.throughput-updates-disabled
     * [throughput_updates_disabled: <boolean> | default = false]
     *
     * # If true, enables retention deletes of DB tables
     * # CLI flag: -table-manager.retention-deletes-enabled
     * [retention_deletes_enabled: <boolean> | default = false]
     *
     * # Tables older than this retention period are deleted. Must be either 0
     * # (disabled) or a multiple of 24h. When enabled, be aware this setting is
     * # destructive to data!
     * # CLI flag: -table-manager.retention-period
     * [retention_period: <duration> | default = 0s]
     *
     * # How frequently to poll backend to learn our capacity.
     * # CLI flag: -table-manager.poll-interval
     * [poll_interval: <duration> | default = 2m]
     *
     * # Periodic tables grace period (duration which table will be created/deleted
     * # before/after it's needed).
     * # CLI flag: -table-manager.periodic-table.grace-period
     * [creation_grace_period: <duration> | default = 10m]
     *
     * index_tables_provisioning:
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.index-table.enable-ondemand-throughput-mode
     *   [enable_ondemand_throughput_mode: <boolean> | default = false]
     *
     *   # Table default write throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.write-throughput
     *   [provisioned_write_throughput: <int> | default = 1000]
     *
     *   # Table default read throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.read-throughput
     *   [provisioned_read_throughput: <int> | default = 300]
     *
     *   write_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.index-table.write-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   read_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.index-table.read-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-enable-ondemand-throughput-mode
     *   [enable_inactive_throughput_on_demand_mode: <boolean> | default = false]
     *
     *   # Table write throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-write-throughput
     *   [inactive_write_throughput: <int> | default = 1]
     *
     *   # Table read throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-read-throughput
     *   [inactive_read_throughput: <int> | default = 300]
     *
     *   inactive_write_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.index-table.inactive-write-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   inactive_read_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.index-table.inactive-read-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   # Number of last inactive tables to enable write autoscale.
     *   # CLI flag: -table-manager.index-table.inactive-write-throughput.scale-last-n
     *   [inactive_write_scale_lastn: <int> | default = 4]
     *
     *   # Number of last inactive tables to enable read autoscale.
     *   # CLI flag: -table-manager.index-table.inactive-read-throughput.scale-last-n
     *   [inactive_read_scale_lastn: <int> | default = 4]
     *
     * chunk_tables_provisioning:
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.chunk-table.enable-ondemand-throughput-mode
     *   [enable_ondemand_throughput_mode: <boolean> | default = false]
     *
     *   # Table default write throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.chunk-table.write-throughput
     *   [provisioned_write_throughput: <int> | default = 1000]
     *
     *   # Table default read throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.chunk-table.read-throughput
     *   [provisioned_read_throughput: <int> | default = 300]
     *
     *   write_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.chunk-table.write-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   read_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.chunk-table.read-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.chunk-table.inactive-enable-ondemand-throughput-mode
     *   [enable_inactive_throughput_on_demand_mode: <boolean> | default = false]
     *
     *   # Table write throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.chunk-table.inactive-write-throughput
     *   [inactive_write_throughput: <int> | default = 1]
     *
     *   # Table read throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.chunk-table.inactive-read-throughput
     *   [inactive_read_throughput: <int> | default = 300]
     *
     *   inactive_write_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   inactive_read_scale:
     *     # Should we enable autoscale for the table.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.enabled
     *     [enabled: <boolean> | default = false]
     *
     *     # AWS AutoScaling role ARN
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.role-arn
     *     [role_arn: <string> | default = ""]
     *
     *     # DynamoDB minimum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.min-capacity
     *     [min_capacity: <int> | default = 3000]
     *
     *     # DynamoDB maximum provision capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.max-capacity
     *     [max_capacity: <int> | default = 6000]
     *
     *     # DynamoDB minimum seconds between each autoscale up.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.out-cooldown
     *     [out_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB minimum seconds between each autoscale down.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.in-cooldown
     *     [in_cooldown: <int> | default = 1800]
     *
     *     # DynamoDB target ratio of consumed capacity to provisioned capacity.
     *     # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale.target-value
     *     [target: <float> | default = 80]
     *
     *   # Number of last inactive tables to enable write autoscale.
     *   # CLI flag: -table-manager.chunk-table.inactive-write-throughput.scale-last-n
     *   [inactive_write_scale_lastn: <int> | default = 4]
     *
     *   # Number of last inactive tables to enable read autoscale.
     *   # CLI flag: -table-manager.chunk-table.inactive-read-throughput.scale-last-n
     *   [inactive_read_scale_lastn: <int> | default = 4]
     */
) {
}