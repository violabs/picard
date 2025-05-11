package io.violabs.picard.starCharts.loki

class GRPCClient(
    /**
     * # gRPC client max receive message size (bytes).
     * # CLI flag: -<prefix>.grpc-max-recv-msg-size
     * [max_recv_msg_size: <int> | default = 104857600]
     *
     * # gRPC client max send message size (bytes).
     * # CLI flag: -<prefix>.grpc-max-send-msg-size
     * [max_send_msg_size: <int> | default = 104857600]
     *
     * # Use compression when sending messages. Supported values are: 'gzip', 'snappy'
     * # and '' (disable compression)
     * # CLI flag: -<prefix>.grpc-compression
     * [grpc_compression: <string> | default = ""]
     *
     * # Rate limit for gRPC client; 0 means disabled.
     * # CLI flag: -<prefix>.grpc-client-rate-limit
     * [rate_limit: <float> | default = 0]
     *
     * # Rate limit burst for gRPC client.
     * # CLI flag: -<prefix>.grpc-client-rate-limit-burst
     * [rate_limit_burst: <int> | default = 0]
     *
     * # Enable backoff and retry when we hit rate limits.
     * # CLI flag: -<prefix>.backoff-on-ratelimits
     * [backoff_on_ratelimits: <boolean> | default = false]
     *
     * backoff_config:
     *   # Minimum delay when backing off.
     *   # CLI flag: -<prefix>.backoff-min-period
     *   [min_period: <duration> | default = 100ms]
     *
     *   # Maximum delay when backing off.
     *   # CLI flag: -<prefix>.backoff-max-period
     *   [max_period: <duration> | default = 10s]
     *
     *   # Number of times to backoff and retry before failing.
     *   # CLI flag: -<prefix>.backoff-retries
     *   [max_retries: <int> | default = 10]
     *
     * # Initial stream window size. Values less than the default are not supported and
     * # are ignored. Setting this to a value other than the default disables the BDP
     * # estimator.
     * # CLI flag: -<prefix>.initial-stream-window-size
     * [initial_stream_window_size: <int> | default = 63KiB1023B]
     *
     * # Initial connection window size. Values less than the default are not supported
     * # and are ignored. Setting this to a value other than the default disables the
     * # BDP estimator.
     * # CLI flag: -<prefix>.initial-connection-window-size
     * [initial_connection_window_size: <int> | default = 63KiB1023B]
     *
     * # Enable TLS in the gRPC client. This flag needs to be enabled when any other
     * # TLS flag is set. If set to false, insecure connection to gRPC server will be
     * # used.
     * # CLI flag: -<prefix>.tls-enabled
     * [tls_enabled: <boolean> | default = false]
     *
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is:
     * # tsdb.shipper.index-gateway-client.grpc
     * [<tls_config>]
     *
     * # The maximum amount of time to establish a connection. A value of 0 means
     * # default gRPC client connect timeout and backoff.
     * # CLI flag: -<prefix>.connect-timeout
     * [connect_timeout: <duration> | default = 5s]
     *
     * # Initial backoff delay after first connection failure. Only relevant if
     * # ConnectTimeout > 0.
     * # CLI flag: -<prefix>.connect-backoff-base-delay
     * [connect_backoff_base_delay: <duration> | default = 1s]
     *
     * # Maximum backoff delay when establishing a connection. Only relevant if
     * # ConnectTimeout > 0.
     * # CLI flag: -<prefix>.connect-backoff-max-delay
     * [connect_backoff_max_delay: <duration> | default = 5s]
     *
     * cluster_validation:
     *   # Optionally define the cluster validation label.
     *   # CLI flag: -<prefix>.cluster-validation.label
     *   [label: <string> | default = ""]
     */
) {
}