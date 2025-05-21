package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.starCharts.loki.Duration
import javax.annotation.processing.Generated

@Generated
data class HttpConfig(
    /**
     *   http:
     *     # The time an idle connection will remain idle before closing.
     *     # CLI flag: -<prefix>.s3.http.idle-conn-timeout
     *     [idle_conn_timeout: <duration> | default = 1m30s]
     */
    val idleConnTimeout: Duration? = null,
    /**
     *     # The amount of time the client will wait for a servers response headers.
     *     # CLI flag: -<prefix>.s3.http.response-header-timeout
     *     [response_header_timeout: <duration> | default = 2m]
     */
    val responseHeaderTimeout: Duration? = null,
    /**
     *     # If the client connects via HTTPS and this option is enabled, the client
     *     # will accept any certificate and hostname.
     *     # CLI flag: -<prefix>.s3.http.insecure-skip-verify
     *     [insecure_skip_verify: <boolean> | default = false]
     */
    val insecureSkipVerify: Boolean? = null,
    /**
     *     # Maximum time to wait for a TLS handshake. 0 means no limit.
     *     # CLI flag: -<prefix>.s3.tls-handshake-timeout
     *     [tls_handshake_timeout: <duration> | default = 10s]
     */
    val tlsHandshakeTimeout: Duration? = null,
    /**
     *     # The time to wait for a server's first response headers after fully writing
     *     # the request headers if the request has an Expect header. 0 to send the
     *     # request body immediately.
     *     # CLI flag: -<prefix>.s3.expect-continue-timeout
     *     [expect_continue_timeout: <duration> | default = 1s]
     */
    val expectContinueTimeout: Duration? = null,
    /**
     *     # Maximum number of idle (keep-alive) connections across all hosts. 0 means
     *     # no limit.
     *     # CLI flag: -<prefix>.s3.max-idle-connections
     *     [max_idle_connections: <int> | default = 100]
     */
    val maxIdleConnections: Int? = null,
    /**
     *     # Maximum number of idle (keep-alive) connections to keep per-host. If 0, a
     *     # built-in default value is used.
     *     # CLI flag: -<prefix>.s3.max-idle-connections-per-host
     *     [max_idle_connections_per_host: <int> | default = 100]
     */
    val maxIdleConnectionsPerHost: Int? = null,
    /**
     *     # Maximum number of connections per host. 0 means no limit.
     *     # CLI flag: -<prefix>.s3.max-connections-per-host
     *     [max_connections_per_host: <int> | default = 0]
     */
    val maxConnectionsPerHost: Int? = null,
    /**
     *     # Path to the CA certificates to validate server certificate against. If not
     *     # set, the host's root CA certificates are used.
     *     # CLI flag: -<prefix>.s3.http.tls-ca-path
     *     [tls_ca_path: <string> | default = ""]
     */
    val tlsCaPath: String? = null,
    /**
     *     # Path to the client certificate, which will be used for authenticating with
     *     # the server. Also requires the key path to be configured.
     *     # CLI flag: -<prefix>.s3.http.tls-cert-path
     *     [tls_cert_path: <string> | default = ""]
     */
    val tlsCertPath: String? = null,
    /**
     *     # Path to the key for the client certificate. Also requires the client
     *     # certificate to be configured.
     *     # CLI flag: -<prefix>.s3.http.tls-key-path
     *     [tls_key_path: <string> | default = ""]
     */
    val tlsKeyPath: String? = null,
    /**
     *     # Override the expected name on the server certificate.
     *     # CLI flag: -<prefix>.s3.http.tls-server-name
     *     [tls_server_name: <string> | default = ""]
     */
    val tlsServerName: String? = null
)