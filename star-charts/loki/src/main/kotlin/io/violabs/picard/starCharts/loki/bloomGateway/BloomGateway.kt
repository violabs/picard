package io.violabs.picard.starCharts.loki.bloomGateway

import io.violabs.picard.dsl.annotation.GeneratedDsl

@GeneratedDsl
class BloomGateway(
    /**
     * # Flag to enable or disable the bloom gateway component globally.
     * # CLI flag: -bloom-gateway.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    val client: BloomGatewayClient? = null,
    /**
     * # Number of workers to use for filtering chunks concurrently. Usually set to 1x
     * # number of CPU cores.
     * # CLI flag: -bloom-gateway.worker-concurrency
     * [worker_concurrency: <int> | default = 4]
     */
    val workerConcurrency: Int? = null,
    /**
     *
     * # Number of blocks processed concurrently on a single worker. Usually set to 2x
     * # number of CPU cores.
     * # CLI flag: -bloom-gateway.block-query-concurrency
     * [block_query_concurrency: <int> | default = 8]
     */
    val blockQueryConcurrency: Int? = null,
    /**
     *
     * # Maximum number of outstanding tasks per tenant.
     * # CLI flag: -bloom-gateway.max-outstanding-per-tenant
     * [max_outstanding_per_tenant: <int> | default = 1024]
     */
    val maxOutstandingPerTenant: Int? = null,
    /**
     *
     * # How many tasks are multiplexed at once.
     * # CLI flag: -bloom-gateway.num-multiplex-tasks
     * [num_multiplex_tasks: <int> | default = 512]
     */
    val numMultiplexTasks: Int? = null
)