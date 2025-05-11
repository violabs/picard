package io.violabs.picard.starCharts.loki.bloomBuild

class BloomBuild(
    /**
     * # Flag to enable or disable the usage of the bloom-planner and bloom-builder
     * # components.
     * # CLI flag: -bloom-build.enabled
     * [enabled: <boolean> | default = false]
     *
     * planner:
     *   # Interval at which to re-run the bloom creation planning.
     *   # CLI flag: -bloom-build.planner.interval
     *   [planning_interval: <duration> | default = 8h]
     *
     *   # Newest day-table offset (from today, inclusive) to build blooms for. 0 start
     *   # building from today, 1 from yesterday and so on. Increase to lower cost by
     *   # not re-writing data to object storage too frequently since recent data
     *   # changes more often at the cost of not having blooms available as quickly.
     *   # CLI flag: -bloom-build.planner.min-table-offset
     *   [min_table_offset: <int> | default = 0]
     *
     *   # Oldest day-table offset (from today, inclusive) to build blooms for. 1 till
     *   # yesterday, 2 till day before yesterday and so on. This can be used to lower
     *   # cost by not trying to build blooms for older data which doesn't change. This
     *   # can be optimized by aligning it with the maximum
     *   # `reject_old_samples_max_age` setting of any tenant.
     *   # CLI flag: -bloom-build.planner.max-table-offset
     *   [max_table_offset: <int> | default = 1]
     *
     *   retention:
     *     # Enable bloom retention.
     *     # CLI flag: -bloom-build.planner.retention.enabled
     *     [enabled: <boolean> | default = false]
     *
     *   queue:
     *     # Maximum number of tasks to queue per tenant.
     *     # CLI flag: -bloom-build.planner.queue.max-tasks-per-tenant
     *     [max_queued_tasks_per_tenant: <int> | default = 30000]
     *
     *     # Whether to store tasks on disk.
     *     # CLI flag: -bloom-build.planner.queue.store-tasks-on-disk
     *     [store_tasks_on_disk: <boolean> | default = false]
     *
     *     # Directory to store tasks on disk.
     *     # CLI flag: -bloom-build.planner.queue.tasks-disk-directory
     *     [tasks_disk_directory: <string> | default = "/tmp/bloom-planner-queue"]
     *
     *     # Whether to clean the tasks directory on startup.
     *     # CLI flag: -bloom-build.planner.queue.clean-tasks-directory
     *     [clean_tasks_directory: <boolean> | default = false]
     *
     * builder:
     *   # The grpc_client block configures the gRPC client used to communicate between
     *   # a client and server component in Loki.
     *   # The CLI flags prefix for this block configuration is:
     *   # bloom-build.builder.grpc
     *   [grpc_config: <grpc_client>]
     *
     *   # Hostname (and port) of the bloom planner
     *   # CLI flag: -bloom-build.builder.planner-address
     *   [planner_address: <string> | default = ""]
     *
     *   backoff_config:
     *     # Minimum delay when backing off.
     *     # CLI flag: -bloom-build.builder.backoff.backoff-min-period
     *     [min_period: <duration> | default = 100ms]
     *
     *     # Maximum delay when backing off.
     *     # CLI flag: -bloom-build.builder.backoff.backoff-max-period
     *     [max_period: <duration> | default = 10s]
     *
     *     # Number of times to backoff and retry before failing.
     *     # CLI flag: -bloom-build.builder.backoff.backoff-retries
     *     [max_retries: <int> | default = 10]
     */
) {
}