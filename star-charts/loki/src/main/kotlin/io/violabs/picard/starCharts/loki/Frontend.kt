package io.violabs.picard.starCharts.loki

class Frontend(
    /**
     * # Log queries that are slower than the specified duration. Set to 0 to disable.
     * # Set to < 0 to enable on all queries.
     * # CLI flag: -frontend.log-queries-longer-than
     * [log_queries_longer_than: <duration> | default = 0s]
     *
     * # Comma-separated list of request header names to include in query logs. Applies
     * # to both query stats and slow queries logs.
     * # CLI flag: -frontend.log-query-request-headers
     * [log_query_request_headers: <string> | default = ""]
     *
     * # Max body size for downstream prometheus.
     * # CLI flag: -frontend.max-body-size
     * [max_body_size: <int> | default = 10485760]
     *
     * # True to enable query statistics tracking. When enabled, a message with some
     * # statistics is logged for every query.
     * # CLI flag: -frontend.query-stats-enabled
     * [query_stats_enabled: <boolean> | default = false]
     *
     * # Maximum number of outstanding requests per tenant per frontend; requests
     * # beyond this error with HTTP 429.
     * # CLI flag: -querier.max-outstanding-requests-per-tenant
     * [max_outstanding_per_tenant: <int> | default = 2048]
     *
     * # In the event a tenant is repeatedly sending queries that lead the querier to
     * # crash or be killed due to an out-of-memory error, the crashed querier will be
     * # disconnected from the query frontend and a new querier will be immediately
     * # assigned to the tenant’s shard. This invalidates the assumption that shuffle
     * # sharding can be used to reduce the impact on tenants. This option mitigates
     * # the impact by configuring a delay between when a querier disconnects because
     * # of a crash and when the crashed querier is actually removed from the tenant's
     * # shard.
     * # CLI flag: -query-frontend.querier-forget-delay
     * [querier_forget_delay: <duration> | default = 0s]
     *
     * # DNS hostname used for finding query-schedulers.
     * # CLI flag: -frontend.scheduler-address
     * [scheduler_address: <string> | default = ""]
     *
     * # How often to resolve the scheduler-address, in order to look for new
     * # query-scheduler instances. Also used to determine how often to poll the
     * # scheduler-ring for addresses if the scheduler-ring is configured.
     * # CLI flag: -frontend.scheduler-dns-lookup-period
     * [scheduler_dns_lookup_period: <duration> | default = 10s]
     *
     * # Number of concurrent workers forwarding queries to single query-scheduler.
     * # CLI flag: -frontend.scheduler-worker-concurrency
     * [scheduler_worker_concurrency: <int> | default = 5]
     *
     * # The grpc_client block configures the gRPC client used to communicate between a
     * # client and server component in Loki.
     * # The CLI flags prefix for this block configuration is:
     * # frontend.grpc-client-config
     * [grpc_client_config: <grpc_client>]
     *
     * # Time to wait for inflight requests to finish before forcefully shutting down.
     * # This needs to be aligned with the query timeout and the graceful termination
     * # period of the process orchestrator.
     * # CLI flag: -frontend.graceful-shutdown-timeout
     * [graceful_shutdown_timeout: <duration> | default = 5m]
     *
     * # Name of network interface to read address from. This address is sent to
     * # query-scheduler and querier, which uses it to send the query response back to
     * # query-frontend.
     * # CLI flag: -frontend.instance-interface-names
     * [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     * # Defines the encoding for requests to and responses from the scheduler and
     * # querier. Can be 'json' or 'protobuf' (defaults to 'json').
     * # CLI flag: -frontend.encoding
     * [encoding: <string> | default = "json"]
     *
     * # Compress HTTP responses.
     * # CLI flag: -querier.compress-http-responses
     * [compress_responses: <boolean> | default = true]
     *
     * # URL of downstream Loki.
     * # CLI flag: -frontend.downstream-url
     * [downstream_url: <string> | default = ""]
     *
     * # URL of querier for tail proxy.
     * # CLI flag: -frontend.tail-proxy-url
     * [tail_proxy_url: <string> | default = ""]
     *
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is: frontend.tail-tls-config
     * [tail_tls_config: <tls_config>]
     *
     * # Support 'application/vnd.apache.parquet' content type in HTTP responses.
     * [support_parquet_encoding: <boolean>]
     */
) {
}