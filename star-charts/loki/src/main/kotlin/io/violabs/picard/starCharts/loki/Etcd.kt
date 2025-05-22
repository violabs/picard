package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.tls.TlsConfig

@GeneratedDsl
data class Etcd(
    /**
     * # The etcd endpoints to connect to.
     * # CLI flag: -<prefix>.etcd.endpoints
     * [endpoints: <list of strings> | default = []]
     */
    val endpoints: List<String>? = null,
    /**
     * # The dial timeout for the etcd connection.
     * # CLI flag: -<prefix>.etcd.dial-timeout
     * [dial_timeout: <duration> | default = 10s]
     */
    val dialTimeout: Duration? = null,
    /**
     * # The maximum number of retries to do for failed ops.
     * # CLI flag: -<prefix>.etcd.max-retries
     * [max_retries: <int> | default = 10]
     */
    val maxRetries: Int? = null,
    /**
     * # Enable TLS.
     * # CLI flag: -<prefix>.etcd.tls-enabled
     * [tls_enabled: <boolean> | default = false]
     */
    val tlsEnabled: Boolean? = null,
    /**
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is: ruler.ring.etcd
     * [<tls_config>]
     */
    val tlsConfig: TlsConfig? = null,
    /**
     * # Etcd username.
     * # CLI flag: -<prefix>.etcd.username
     * [username: <string> | default = ""]
     */
    val username: String? = null,
    /**
     * # Etcd password.
     * # CLI flag: -<prefix>.etcd.password
     * [password: <string> | default = ""]
     */
    val password: String? = null,
)
