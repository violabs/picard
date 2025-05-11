package io.violabs.picard.starCharts.loki

class IngesterClient(
    /**
     * # Configures how connections are pooled.
     * pool_config:
     *   # How frequently to clean up clients for ingesters that have gone away.
     *   # CLI flag: -distributor.client-cleanup-period
     *   [client_cleanup_period: <duration> | default = 15s]
     *
     *   # Run a health check on each ingester client during periodic cleanup.
     *   # CLI flag: -distributor.health-check-ingesters
     *   [health_check_ingesters: <boolean> | default = true]
     *
     *   # How quickly a dead client will be removed after it has been detected to
     *   # disappear. Set this to a value to allow time for a secondary health check to
     *   # recover the missing client.
     *   # CLI flag: -ingester.client.healthcheck-timeout
     *   [remote_timeout: <duration> | default = 1s]
     *
     * # The remote request timeout on the client side.
     * # CLI flag: -ingester.client.timeout
     * [remote_timeout: <duration> | default = 5s]
     *
     * # Configures how the gRPC connection to ingesters work as a client.
     * # The CLI flags prefix for this block configuration is: ingester.client
     * [grpc_client_config: <grpc_client>]
     */
) {
}