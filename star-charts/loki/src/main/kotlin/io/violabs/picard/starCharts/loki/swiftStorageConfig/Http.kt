package io.violabs.picard.starCharts.loki.swiftStorageConfig

data class Http(
    /**
     *   # Path to the CA certificates to validate server certificate against. If not
     *   # set, the host's root CA certificates are used.
     *   # CLI flag: -<prefix>.swift.http.tls-ca-path
     *   [tls_ca_path: <string> | default = ""]
     */
    val tlsCaPath: String? = null
)