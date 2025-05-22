package io.violabs.picard.starCharts.loki.cosStorageConfig

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class HTTPConfig(
    /**
     *   # The maximum amount of time an idle connection will be held open.
     *   # CLI flag: -<prefix>.cos.http.idle-conn-timeout
     *   [idle_conn_timeout: <duration> | default = 1m30s]
     */
    val idleConnTimeout: Duration? = null,
    /**
     *   # If non-zero, specifies the amount of time to wait for a server's response
     *   # headers after fully writing the request.
     *   # CLI flag: -<prefix>.cos.http.response-header-timeout
     *   [response_header_timeout: <duration> | default = 0s]
     */
    val responseHeaderTimeout: Duration? = null,
)
