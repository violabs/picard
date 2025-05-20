package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.ring.SchedulerRing

@GeneratedDSL
data class QueryScheduler(
    /**
     * # Maximum number of outstanding requests per tenant per query-scheduler.
     * # In-flight requests above this limit will fail with HTTP response status code
     * # 429.
     * # CLI flag: -query-scheduler.max-outstanding-requests-per-tenant
     * [max_outstanding_requests_per_tenant: <int> | default = 32000]
     */
    val maxOutstandingRequestsPerTenant: Int? = null,
    /**
     * # Maximum number of levels of nesting of hierarchical queues. 0 means that
     * # hierarchical queues are disabled.
     * # CLI flag: -query-scheduler.max-queue-hierarchy-levels
     * [max_queue_hierarchy_levels: <int> | default = 3]
     */
    val maxQueueHierarchyLevels: Int? = null,
    /**
     * # If a querier disconnects without sending notification about graceful shutdown,
     * # the query-scheduler will keep the querier in the tenant's shard until the
     * # forget delay has passed. This feature is useful to reduce the blast radius
     * # when shuffle-sharding is enabled.
     * # CLI flag: -query-scheduler.querier-forget-delay
     * [querier_forget_delay: <duration> | default = 0s]
     */
    val querierForgetDelay: Duration? = null,
    /**
     * # This configures the gRPC client used to report errors back to the
     * # query-frontend.
     * # The CLI flags prefix for this block configuration is:
     * # query-scheduler.grpc-client-config
     * [grpc_client_config: <grpc_client>]
     */
    val grpcClientConfig: GRPCClient? = null,
    /**
     * # Set to true to have the query schedulers create and place themselves in a
     * # ring. If no frontend_address or scheduler_address are present anywhere else in
     * # the configuration, Loki will toggle this value to true.
     * # CLI flag: -query-scheduler.use-scheduler-ring
     * [use_scheduler_ring: <boolean> | default = false]
     */
    val useSchedulerRing: Boolean? = null,
    /**
     * # The hash ring configuration. This option is required only if
     * # use_scheduler_ring is true.
     */
    val schedulerRing: SchedulerRing? = null,
)
