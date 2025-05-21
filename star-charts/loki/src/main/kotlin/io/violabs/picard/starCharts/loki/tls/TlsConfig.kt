package io.violabs.picard.starCharts.loki.tls

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.starCharts.serialization.ListToCommaSeparatedSerializer

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class TlsConfig(
    /**
     * # Path to the client certificate, which will be used for authenticating with the
     * # server. Also requires the key path to be configured.
     * # CLI flag: -<prefix>.tls-cert-path
     * [tls_cert_path: <string> | default = ""]
     */
    val tlsCertPath: String? = null,
    /**
     * # Path to the key for the client certificate. Also requires the client
     * # certificate to be configured.
     * # CLI flag: -<prefix>.tls-key-path
     * [tls_key_path: <string> | default = ""]
     */
    val tlsKeyPath: String? = null,
    /**
     * # Path to the CA certificates to validate server certificate against. If not
     * # set, the host's root CA certificates are used.
     * # CLI flag: -<prefix>.tls-ca-path
     * [tls_ca_path: <string> | default = ""]
     */
    val tlsCaPath: String? = null,
    /**
     * # Override the expected name on the server certificate.
     * # CLI flag: -<prefix>.tls-server-name
     * [tls_server_name: <string> | default = ""]
     */
    val tlsServerName: String? = null,
    /**
     * # Skip validating server certificate.
     * # CLI flag: -<prefix>.tls-insecure-skip-verify
     * [tls_insecure_skip_verify: <boolean> | default = false]
     */
    val tlsInsecureSkipVerify: Boolean? = null,
    /**
     * # Override the default cipher suite list (separated by commas). Allowed values:
     * #
     * # Secure Ciphers:
     * # - TLS_AES_128_GCM_SHA256
     * # - TLS_AES_256_GCM_SHA384
     * # - TLS_CHACHA20_POLY1305_SHA256
     * # - TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA
     * # - TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA
     * # - TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA
     * # - TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA
     * # - TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256
     * # - TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384
     * # - TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
     * # - TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384
     * # - TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256
     * # - TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256
     * #
     * # Insecure Ciphers:
     * # - TLS_RSA_WITH_RC4_128_SHA
     * # - TLS_RSA_WITH_3DES_EDE_CBC_SHA
     * # - TLS_RSA_WITH_AES_128_CBC_SHA
     * # - TLS_RSA_WITH_AES_256_CBC_SHA
     * # - TLS_RSA_WITH_AES_128_CBC_SHA256
     * # - TLS_RSA_WITH_AES_128_GCM_SHA256
     * # - TLS_RSA_WITH_AES_256_GCM_SHA384
     * # - TLS_ECDHE_ECDSA_WITH_RC4_128_SHA
     * # - TLS_ECDHE_RSA_WITH_RC4_128_SHA
     * # - TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA
     * # - TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256
     * # - TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256
     * # CLI flag: -<prefix>.tls-cipher-suites
     * [tls_cipher_suites: <string> | default = ""]
     */
    @JsonSerialize(using = ListToCommaSeparatedSerializer::class)
    val tlsCipherSuites: List<Cipher>? = null,
    /**
     * # Override the default minimum TLS version. Allowed values: VersionTLS10,
     * # VersionTLS11, VersionTLS12, VersionTLS13
     * # CLI flag: -<prefix>.tls-min-version
     * [tls_min_version: <string> | default = ""]
     */
    val tlsMinVersion: TLSVersion? = null,
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class HTTP(
        /**
         *   # Server TLS certificate. This configuration parameter is YAML only.
         *   [cert: <string> | default = ""]
         */
        val cert: String? = null,
        /**
         *   # Server TLS key. This configuration parameter is YAML only.
         *   [key: <string> | default = ""]
         */
        val key: String? = null,
        /**
         *   # Root certificate authority used to verify client certificates. This
         *   # configuration parameter is YAML only.
         *   [client_ca: <string> | default = ""]
         */
        @JsonProperty("client_ca")
        val clientCA: String? = null,
        /**
         *   # HTTP server cert path.
         *   # CLI flag: -server.http-tls-cert-path
         *   [cert_file: <string> | default = ""]
         */
        val certFile: String? = null,
        /**
         *   # HTTP server key path.
         *   # CLI flag: -server.http-tls-key-path
         *   [key_file: <string> | default = ""]
         */
        val keyFile: String? = null,
        /**
         *   # HTTP TLS Client Auth type.
         *   # CLI flag: -server.http-tls-client-auth
         *   [client_auth_type: <string> | default = ""]
         */
        val clientAuthType: String? = null,
        /**
         *   # HTTP TLS Client CA path.
         *   # CLI flag: -server.http-tls-ca-path
         *   [client_ca_file: <string> | default = ""]
         */
        @JsonProperty("client_ca_file")
        val clientCAFile: String? = null
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class GRPC(
        /**
         *   # Server TLS certificate. This configuration parameter is YAML only.
         *   [cert: <string> | default = ""]
         */
        val cert: String? = null,
        /**
         *   # Server TLS key. This configuration parameter is YAML only.
         *   [key: <string> | default = ""]
         */
        val key: String? = null,
        /**
         *   # Root certificate authority used to verify client certificates. This
         *   # configuration parameter is YAML only.
         *   [client_ca: <string> | default = ""]
         */
        @JsonProperty("client_ca")
        val clientCA: String? = null,
        /**
         *   # GRPC TLS server cert path.
         *   # CLI flag: -server.grpc-tls-cert-path
         *   [cert_file: <string> | default = ""]
         */
        val certFile: String? = null,
        /**
         *   # GRPC TLS server key path.
         *   # CLI flag: -server.grpc-tls-key-path
         *   [key_file: <string> | default = ""]
         */
        val keyFile: String? = null,
        /**
         *   # GRPC TLS Client Auth type.
         *   # CLI flag: -server.grpc-tls-client-auth
         *   [client_auth_type: <string> | default = ""]
         */
        val clientAuthType: String? = null,
        /**
         *   # GRPC TLS Client CA path.
         *   # CLI flag: -server.grpc-tls-ca-path
         *   [client_ca_file: <string> | default = ""]
         */
        @JsonProperty("client_ca_file")
        val clientCAFile: String? = null
    )
}