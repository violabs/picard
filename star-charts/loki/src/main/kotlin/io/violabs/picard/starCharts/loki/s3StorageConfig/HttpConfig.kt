package io.violabs.picard.starCharts.loki.s3StorageConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL
data class HttpConfig(
    /**
     *   # Timeout specifies a time limit for requests made by s3 Client.
     *   # CLI flag: -<prefix>.s3.http.timeout
     *   [timeout: <duration> | default = 0s]
     */
    val timeout: Duration? = null,
    /**
     *   # The maximum amount of time an idle connection will be held open.
     *   # CLI flag: -<prefix>.s3.http.idle-conn-timeout
     *   [idle_conn_timeout: <duration> | default = 1m30s]
     */
    val idleConnTimeout: Duration? = null,
    /**
     *   # If non-zero, specifies the amount of time to wait for a server's response
     *   # headers after fully writing the request.
     *   # CLI flag: -<prefix>.s3.http.response-header-timeout
     *   [response_header_timeout: <duration> | default = 0s]
     */
    val responseHeaderTimeout: Duration? = null,
    /**
     *   # Set to true to skip verifying the certificate chain and hostname.
     *   # CLI flag: -<prefix>.s3.http.insecure-skip-verify
     *   [insecure_skip_verify: <boolean> | default = false]
     */
    val insecureSkipVerify: Boolean? = null,
    /**
     *   # Path to the trusted CA file that signed the SSL certificate of the S3
     *   # endpoint.
     *   # CLI flag: -<prefix>.s3.http.ca-file
     *   [ca_file: <string> | default = ""]
     */
    val caFile: String? = null
)