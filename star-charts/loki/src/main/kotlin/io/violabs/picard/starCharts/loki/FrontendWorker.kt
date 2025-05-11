package io.violabs.picard.starCharts.loki

class FrontendWorker(
    /**
     * # Address of query frontend service, in host:port format. If
     * # -querier.scheduler-address is set as well, querier will use scheduler instead.
     * # Only one of -querier.frontend-address or -querier.scheduler-address can be
     * # set. If neither is set, queries are only received via HTTP endpoint.
     * # CLI flag: -querier.frontend-address
     * [frontend_address: <string> | default = ""]
     *
     * # Hostname (and port) of scheduler that querier will periodically resolve,
     * # connect to and receive queries from. Only one of -querier.frontend-address or
     * # -querier.scheduler-address can be set. If neither is set, queries are only
     * # received via HTTP endpoint.
     * # CLI flag: -querier.scheduler-address
     * [scheduler_address: <string> | default = ""]
     *
     * # How often to query DNS for query-frontend or query-scheduler address. Also
     * # used to determine how often to poll the scheduler-ring for addresses if the
     * # scheduler-ring is configured.
     * # CLI flag: -querier.dns-lookup-period
     * [dns_lookup_duration: <duration> | default = 3s]
     *
     * # Querier ID, sent to frontend service to identify requests from the same
     * # querier. Defaults to hostname.
     * # CLI flag: -querier.id
     * [id: <string> | default = ""]
     *
     * # Configures the querier gRPC client used to communicate with the
     * # query-frontend. This can't be used in conjunction with 'grpc_client_config'.
     * # The CLI flags prefix for this block configuration is:
     * # querier.frontend-grpc-client
     * [query_frontend_grpc_client: <grpc_client>]
     *
     * # Configures the querier gRPC client used to communicate with the query-frontend
     * # and with the query-scheduler. This can't be used in conjunction with
     * # 'query_frontend_grpc_client' or 'query_scheduler_grpc_client'.
     * # The CLI flags prefix for this block configuration is: querier.frontend-client
     * [grpc_client_config: <grpc_client>]
     *
     * # Configures the querier gRPC client used to communicate with the
     * # query-scheduler. This can't be used in conjunction with 'grpc_client_config'.
     * # The CLI flags prefix for this block configuration is:
     * # querier.scheduler-grpc-client
     * [query_scheduler_grpc_client: <grpc_client>]
     */
) {
}