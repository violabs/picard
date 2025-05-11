package io.violabs.picard.starCharts.loki

class ETCD(
    /**
     * # The etcd endpoints to connect to.
     * # CLI flag: -<prefix>.etcd.endpoints
     * [endpoints: <list of strings> | default = []]
     *
     * # The dial timeout for the etcd connection.
     * # CLI flag: -<prefix>.etcd.dial-timeout
     * [dial_timeout: <duration> | default = 10s]
     *
     * # The maximum number of retries to do for failed ops.
     * # CLI flag: -<prefix>.etcd.max-retries
     * [max_retries: <int> | default = 10]
     *
     * # Enable TLS.
     * # CLI flag: -<prefix>.etcd.tls-enabled
     * [tls_enabled: <boolean> | default = false]
     *
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is: ruler.ring.etcd
     * [<tls_config>]
     *
     * # Etcd username.
     * # CLI flag: -<prefix>.etcd.username
     * [username: <string> | default = ""]
     *
     * # Etcd password.
     * # CLI flag: -<prefix>.etcd.password
     * [password: <string> | default = ""]
     */
) {
}