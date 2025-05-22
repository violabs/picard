package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.clusterValidation.ClusterValidation
import io.violabs.picard.starCharts.loki.tls.TLSVersion
import io.violabs.picard.starCharts.loki.tls.TlsConfig
import io.violabs.picard.starCharts.serialization.ListToCommaSeparatedSerializer

@GeneratedDsl
data class Server(
    /**
     * # HTTP server listen network, default tcp
     * # CLI flag: -server.http-listen-network
     * [http_listen_network: <string> | default = "tcp"]
     */
    val httpListenNetwork: String? = null,
    /**
     * # HTTP server listen address.
     * # CLI flag: -server.http-listen-address
     * [http_listen_address: <string> | default = ""]
     */
    val httpListenAddress: String? = null,
    /**
     * # HTTP server listen port.
     * # CLI flag: -server.http-listen-port
     * [http_listen_port: <int> | default = 3100]
     */
    val httpListenPort: Int? = null,
    /**
     * # Maximum number of simultaneous http connections, <=0 to disable
     * # CLI flag: -server.http-conn-limit
     * [http_listen_conn_limit: <int> | default = 0]
     */
    val httpListenConnLimit: Int? = null,
    /**
     * # gRPC server listen network
     * # CLI flag: -server.grpc-listen-network
     * [grpc_listen_network: <string> | default = "tcp"]
     */
    val grpcListenNetwork: String? = null,
    /**
     * # gRPC server listen address.
     * # CLI flag: -server.grpc-listen-address
     * [grpc_listen_address: <string> | default = ""]
     */
    val grpcListenAddress: String? = null,
    /**
     * # gRPC server listen port.
     * # CLI flag: -server.grpc-listen-port
     * [grpc_listen_port: <int> | default = 9095]
     */
    val grpcListenPort: Int? = null,
    /**
     * # Maximum number of simultaneous grpc connections, <=0 to disable
     * # CLI flag: -server.grpc-conn-limit
     * [grpc_listen_conn_limit: <int> | default = 0]
     */
    val grpcListenConnLimit: Int? = null,
    /**
     * # Enables PROXY protocol.
     * # CLI flag: -server.proxy-protocol-enabled
     * [proxy_protocol_enabled: <boolean> | default = false]
     */
    val proxyProtocolEnabled: Boolean? = null,
    /**
     * # Comma-separated list of cipher suites to use. If blank, the default Go cipher
     * # suites is used.
     * # CLI flag: -server.tls-cipher-suites
     * [tls_cipher_suites: <string> | default = ""]
     */
    @JsonSerialize(using = ListToCommaSeparatedSerializer::class)
    val tlsCipherSuites: List<String>? = null,
    /**
     * # Minimum TLS version to use. Allowed values: VersionTLS10, VersionTLS11,
     * # VersionTLS12, VersionTLS13. If blank, the Go TLS minimum version is used.
     * # CLI flag: -server.tls-min-version
     * [tls_min_version: <string> | default = ""]
     */
    val tlsMinVersion: TLSVersion? = null,
    val httpTLSConfig: TlsConfig.HTTP? = null,
    val grpcTLSConfig: TlsConfig.GRPC? = null,
    /**
     * # Register the intrumentation handlers (/metrics etc).
     * # CLI flag: -server.register-instrumentation
     * [register_instrumentation: <boolean> | default = true]
     */
    val registerInstrumentation: Boolean? = null,
    /**
     * # If set to true, gRPC statuses will be reported in instrumentation labels with
     * # their string representations. Otherwise, they will be reported as "error".
     * # CLI flag: -server.report-grpc-codes-in-instrumentation-label-enabled
     * [report_grpc_codes_in_instrumentation_label_enabled: <boolean> | default = false]
     */
    val reportGrpcCodesInInstrumentationLabelEnabled: Boolean? = null,
    /**
     * # Timeout for graceful shutdowns
     * # CLI flag: -server.graceful-shutdown-timeout
     * [graceful_shutdown_timeout: <duration> | default = 30s]
     */
    val gracefulShutdownTimeout: Duration? = null,
    /**
     * # Read timeout for entire HTTP request, including headers and body.
     * # CLI flag: -server.http-read-timeout
     * [http_server_read_timeout: <duration> | default = 30s]
     */
    val httpServerReadTimeout: Duration? = null,
    /**
     * # Read timeout for HTTP request headers. If set to 0, value of
     * # -server.http-read-timeout is used.
     * # CLI flag: -server.http-read-header-timeout
     * [http_server_read_header_timeout: <duration> | default = 0s]
     */
    val httpServerReadHeaderTimeout: Duration? = null,
    /**
     * # Write timeout for HTTP server
     * # CLI flag: -server.http-write-timeout
     * [http_server_write_timeout: <duration> | default = 30s]
     */
    val httpServerWriteTimeout: Duration? = null,
    /**
     * # Idle timeout for HTTP server
     * # CLI flag: -server.http-idle-timeout
     * [http_server_idle_timeout: <duration> | default = 2m]
     */
    val httpServerIdleTimeout: Duration? = null,
    /**
     * # Log closed connections that did not receive any response, most likely because
     * # client didn't send any request within timeout.
     * # CLI flag: -server.http-log-closed-connections-without-response-enabled
     * [http_log_closed_connections_without_response_enabled: <boolean> | default = false]
     */
    val httpLogClosedConnectionsWithoutResponseEnabled: Boolean? = null,
    /**
     * # Limit on the size of a gRPC message this server can receive (bytes).
     * # CLI flag: -server.grpc-max-recv-msg-size-bytes
     * [grpc_server_max_recv_msg_size: <int> | default = 4194304]
     */
    val grpcServerMaxRecvMsgSize: Int? = null,
    /**
     * # Limit on the size of a gRPC message this server can send (bytes).
     * # CLI flag: -server.grpc-max-send-msg-size-bytes
     * [grpc_server_max_send_msg_size: <int> | default = 4194304]
     */
    val grpcServerMaxSendMsgSize: Int? = null,
    /**
     * # Limit on the number of concurrent streams for gRPC calls per client connection
     * # (0 = unlimited)
     * # CLI flag: -server.grpc-max-concurrent-streams
     * [grpc_server_max_concurrent_streams: <int> | default = 100]
     */
    val grpcServerMaxConcurrentStreams: Int? = null,
    /**
     * # The duration after which an idle connection should be closed. Default:
     * # infinity
     * # CLI flag: -server.grpc.keepalive.max-connection-idle
     * [grpc_server_max_connection_idle: <duration> | default = 2562047h47m16.854775807s]
     */
    val grpcServerMaxConnectionIdle: Duration? = null,
    /**
     * # The duration for the maximum amount of time a connection may exist before it
     * # will be closed. Default: infinity
     * # CLI flag: -server.grpc.keepalive.max-connection-age
     * [grpc_server_max_connection_age: <duration> | default = 2562047h47m16.854775807s]
     */
    val grpcServerMaxConnectionAge: Duration? = null,
    /**
     * # An additive period after max-connection-age after which the connection will be
     * # forcibly closed. Default: infinity
     * # CLI flag: -server.grpc.keepalive.max-connection-age-grace
     * [grpc_server_max_connection_age_grace: <duration> | default = 2562047h47m16.854775807s]
     */
    val grpcServerMaxConnectionAgeGrace: Duration? = null,
    /**
     * # Duration after which a keepalive probe is sent in case of no activity over the
     * # connection., Default: 2h
     * # CLI flag: -server.grpc.keepalive.time
     * [grpc_server_keepalive_time: <duration> | default = 2h]
     */
    val grpcServerKeepaliveTime: Duration? = null,
    /**
     * # After having pinged for keepalive check, the duration after which an idle
     * # connection should be closed, Default: 20s
     * # CLI flag: -server.grpc.keepalive.timeout
     * [grpc_server_keepalive_timeout: <duration> | default = 20s]
     */
    val grpcServerKeepaliveTimeout: Duration? = null,
    /**
     * # Minimum amount of time a client should wait before sending a keepalive ping.
     * # If client sends keepalive ping more often, server will send GOAWAY and close
     * # the connection.
     * # CLI flag: -server.grpc.keepalive.min-time-between-pings
     * [grpc_server_min_time_between_pings: <duration> | default = 10s]
     */
    val grpcServerMinTimeBetweenPings: Duration? = null,
    /**
     * # If true, server allows keepalive pings even when there are no active
     * # streams(RPCs). If false, and client sends ping when there are no active
     * # streams, server will send GOAWAY and close the connection.
     * # CLI flag: -server.grpc.keepalive.ping-without-stream-allowed
     * [grpc_server_ping_without_stream_allowed: <boolean> | default = true]
     */
    val grpcServerPingWithoutStreamAllowed: Boolean? = null,
    /**
     * # If non-zero, configures the amount of GRPC server workers used to serve the
     * # requests.
     * # CLI flag: -server.grpc.num-workers
     * [grpc_server_num_workers: <int> | default = 0]
     */
    val grpcServerNumWorkers: Int? = null,
    /**
     * # If true, the request_message_bytes, response_message_bytes, and
     * # inflight_requests metrics will be tracked. Enabling this option prevents the
     * # use of memory pools for parsing gRPC request bodies and may lead to more
     * # memory allocations.
     * # CLI flag: -server.grpc.stats-tracking-enabled
     * [grpc_server_stats_tracking_enabled: <boolean> | default = true]
     */
    val grpcServerStatsTrackingEnabled: Boolean? = null,
    /**
     * # Deprecated option, has no effect and will be removed in a future version.
     * # CLI flag: -server.grpc.recv-buffer-pools-enabled
     * [grpc_server_recv_buffer_pools_enabled: <boolean> | default = false]
     */
    val grpcServerRecvBufferPoolsEnabled: Boolean? = null,
    /**
     * # Output log messages in the given format. Valid formats: [logfmt, json]
     * # CLI flag: -log.format
     * [log_format: <string> | default = "logfmt"]
     */
    val logFormat: String? = null,
    /**
     * # Only log messages with the given severity or above. Valid levels: [debug,
     * # info, warn, error]
     * # CLI flag: -log.level
     * [log_level: <string> | default = "info"]
     */
    val logLevel: LokiLogLevel? = null,
    /**
     * # Optionally log the source IPs.
     * # CLI flag: -server.log-source-ips-enabled
     * [log_source_ips_enabled: <boolean> | default = false]
     */
    val logSourceIpsEnabled: Boolean? = null,
    /**
     * # Log all source IPs instead of only the originating one. Only used if
     * # server.log-source-ips-enabled is true
     * # CLI flag: -server.log-source-ips-full
     * [log_source_ips_full: <boolean> | default = false]
     */
    val logSourceIpsFull: Boolean? = null,
    /**
     * # Header field storing the source IPs. Only used if
     * # server.log-source-ips-enabled is true. If not set the default Forwarded,
     * # X-Real-IP and X-Forwarded-For headers are used
     * # CLI flag: -server.log-source-ips-header
     * [log_source_ips_header: <string> | default = ""]
     */
    val logSourceIpsHeader: String? = null,
    /**
     * # Regex for matching the source IPs. Only used if server.log-source-ips-enabled
     * # is true. If not set the default Forwarded, X-Real-IP and X-Forwarded-For
     * # headers are used
     * # CLI flag: -server.log-source-ips-regex
     * [log_source_ips_regex: <string> | default = ""]
     */
    val logSourceIpsRegex: String? = null,
    /**
     * # Optionally log request headers.
     * # CLI flag: -server.log-request-headers
     * [log_request_headers: <boolean> | default = false]
     */
    val logRequestHeaders: Boolean? = null,
    /**
     * # Optionally log requests at info level instead of debug level. Applies to
     * # request headers as well if server.log-request-headers is enabled.
     * # CLI flag: -server.log-request-at-info-level-enabled
     * [log_request_at_info_level_enabled: <boolean> | default = false]
     */
    val logRequestAtInfoLevelEnabled: Boolean? = null,
    /**
     * # Comma separated list of headers to exclude from loggin. Only used if
     * # server.log-request-headers is true.
     * # CLI flag: -server.log-request-headers-exclude-list
     * [log_request_exclude_headers_list: <string> | default = ""]
     */
    @JsonSerialize(using = ListToCommaSeparatedSerializer::class)
    val logRequestExcludeHeadersList: String? = null,
    /**
     * # Base path to serve all API routes from (e.g. /v1/)
     * # CLI flag: -server.path-prefix
     * [http_path_prefix: <string> | default = ""]
     */
    val httpPathPrefix: String? = null,
    val clusterValidation: ClusterValidation? = null
)