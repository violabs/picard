package io.violabs.picard.starCharts.loki

class BloomGateway(
    /**
     * # Flag to enable or disable the bloom gateway component globally.
     * # CLI flag: -bloom-gateway.enabled
     * [enabled: <boolean> | default = false]
     *
     * client:
     *   # Configures the behavior of the connection pool.
     *   pool_config:
     *     # How frequently to update the list of servers.
     *     # CLI flag: -bloom-gateway-client.pool.check-interval
     *     [check_interval: <duration> | default = 15s]
     *
     *   # The grpc_client block configures the gRPC client used to communicate between
     *   # a client and server component in Loki.
     *   # The CLI flags prefix for this block configuration is:
     *   # bloom-gateway-client.grpc
     *   [grpc_client_config: <grpc_client>]
     *
     *   # Comma separated addresses list in DNS Service Discovery format:
     *   # https://grafana.com/docs/mimir/latest/configure/about-dns-service-discovery/#supported-discovery-modes
     *   # CLI flag: -bloom-gateway-client.addresses
     *   [addresses: <string> | default = ""]
     *
     * # Number of workers to use for filtering chunks concurrently. Usually set to 1x
     * # number of CPU cores.
     * # CLI flag: -bloom-gateway.worker-concurrency
     * [worker_concurrency: <int> | default = 4]
     *
     * # Number of blocks processed concurrently on a single worker. Usually set to 2x
     * # number of CPU cores.
     * # CLI flag: -bloom-gateway.block-query-concurrency
     * [block_query_concurrency: <int> | default = 8]
     *
     * # Maximum number of outstanding tasks per tenant.
     * # CLI flag: -bloom-gateway.max-outstanding-per-tenant
     * [max_outstanding_per_tenant: <int> | default = 1024]
     *
     * # How many tasks are multiplexed at once.
     * # CLI flag: -bloom-gateway.num-multiplex-tasks
     * [num_multiplex_tasks: <int> | default = 512]
     */
) {
}