package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
class BloomBuildPlannerQueue(
    /**
     * # Maximum number of tasks to queue per tenant.
     * # CLI flag: -bloom-build.planner.queue.max-tasks-per-tenant
     * [max_queued_tasks_per_tenant: <int> | default = 30000]
     */
    val maxQueuedTasksPerTenant: Int? = null,
    /**
     * # Whether to store tasks on disk.
     * # CLI flag: -bloom-build.planner.queue.store-tasks-on-disk
     * [store_tasks_on_disk: <boolean> | default = false]
     */
    val storeTasksOnDisk: Boolean? = null,
    /**
     * # Directory to store tasks on disk.
     * # CLI flag: -bloom-build.planner.queue.tasks-disk-directory
     * [tasks_disk_directory: <string> | default = "/tmp/bloom-planner-queue"]
     */
    val tasksDiskDirectory: String? = null,
    /**
     * # Whether to clean the tasks directory on startup.
     * # CLI flag: -bloom-build.planner.queue.clean-tasks-directory
     * [clean_tasks_directory: <boolean> | default = false]
     */
    val cleanTasksDirectory: Boolean? = null
)