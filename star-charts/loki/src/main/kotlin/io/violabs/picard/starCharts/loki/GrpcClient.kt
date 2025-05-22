package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.tls.TlsConfig
import io.violabs.picard.starCharts.loki.clusterValidation.ClusterValidation

@GeneratedDsl
class GrpcClient(
    /**
     * # gRPC client max receive message size (bytes).
     * # CLI flag: -<prefix>.grpc-max-recv-msg-size
     * [max_recv_msg_size: <int> | default = 104857600]
     */
    val maxRecvMsgSize: Int? = null,
    /**
     * # gRPC client max send message size (bytes).
     * # CLI flag: -<prefix>.grpc-max-send-msg-size
     * [max_send_msg_size: <int> | default = 104857600]
     */
    val maxSendMsgSize: Int? = null,
    /**
     * # Use compression when sending messages. Supported values are: 'gzip', 'snappy'
     * # and '' (disable compression)
     * # CLI flag: -<prefix>.grpc-compression
     * [grpc_compression: <string> | default = ""]
     */
    val grpcCompression: String? = null,
    /**
     * # Rate limit for gRPC client; 0 means disabled.
     * # CLI flag: -<prefix>.grpc-client-rate-limit
     * [rate_limit: <float> | default = 0]
     */
    val rateLimit: Float? = null,
    /**
     * # Rate limit burst for gRPC client.
     * # CLI flag: -<prefix>.grpc-client-rate-limit-burst
     * [rate_limit_burst: <int> | default = 0]
     */
    val rateLimitBurst: Int? = null,
    /**
     * # Enable backoff and retry when we hit rate limits.
     * # CLI flag: -<prefix>.backoff-on-ratelimits
     * [backoff_on_ratelimits: <boolean> | default = false]
     */
    val backoffOnRatelimits: Boolean? = null,
    /**
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
     */
    val backoffConfig: BackoffConfig? = null,
    /**
     * # Initial stream window size. Values less than the default are not supported and
     * # are ignored. Setting this to a value other than the default disables the BDP
     * # estimator.
     * # CLI flag: -<prefix>.initial-stream-window-size
     * [initial_stream_window_size: <int> | default = 63KiB1023B]
     */
    val initialStreamWindowSize: Int? = null,
    /**
     * # Initial connection window size. Values less than the default are not supported
     * # and are ignored. Setting this to a value other than the default disables the
     * # BDP estimator.
     * # CLI flag: -<prefix>.initial-connection-window-size
     * [initial_connection_window_size: <int> | default = 63KiB1023B]
     */
    val initialConnectionWindowSize: Int? = null,
    /**
     * # Enable TLS in the gRPC client. This flag needs to be enabled when any other
     * # TLS flag is set. If set to false, insecure connection to gRPC server will be
     * # used.
     * # CLI flag: -<prefix>.tls-enabled
     * [tls_enabled: <boolean> | default = false]
     */
    val tlsEnabled: Boolean? = null,
    /**
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is:
     * # tsdb.shipper.index-gateway-client.grpc
     * [<tls_config>]
     */
    val tlsConfig: TlsConfig? = null,
    /**
     * # The maximum amount of time to establish a connection. A value of 0 means
     * # default gRPC client connect timeout and backoff.
     * # CLI flag: -<prefix>.connect-timeout
     * [connect_timeout: <duration> | default = 5s]
     */
    val connectTimeout: Duration? = null,
    /**
     * # Initial backoff delay after first connection failure. Only relevant if
     * # ConnectTimeout > 0.
     * # CLI flag: -<prefix>.connect-backoff-base-delay
     * [connect_backoff_base_delay: <duration> | default = 1s]
     */
    val connectBackoffBaseDelay: Duration? = null,
    /**
     * # Maximum backoff delay when establishing a connection. Only relevant if
     * # ConnectTimeout > 0.
     * # CLI flag: -<prefix>.connect-backoff-max-delay
     * [connect_backoff_max_delay: <duration> | default = 5s]
     */
    val connectBackoffMaxDelay: Duration? = null,
    /**
     * cluster_validation:
     *   # Optionally define the cluster validation label.
     *   # CLI flag: -<prefix>.cluster-validation.label
     *   [label: <string> | default = ""]
     */
    val clusterValidation: ClusterValidation? = null,
)
