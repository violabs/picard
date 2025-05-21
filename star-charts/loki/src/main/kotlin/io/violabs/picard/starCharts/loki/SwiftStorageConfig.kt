package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL(
    withMapGroup = "SINGLE"
)
class SwiftStorageConfig(
    /**
     * # OpenStack Swift authentication API version. 0 to autodetect.
     * # CLI flag: -<prefix>.swift.auth-version
     * [auth_version: <int> | default = 0]
     *
     * # OpenStack Swift authentication URL
     * # CLI flag: -<prefix>.swift.auth-url
     * [auth_url: <string> | default = ""]
     *
     * # Set this to true to use the internal OpenStack Swift endpoint URL
     * # CLI flag: -<prefix>.swift.internal
     * [internal: <boolean> | default = false]
     *
     * # OpenStack Swift username.
     * # CLI flag: -<prefix>.swift.username
     * [username: <string> | default = ""]
     *
     * # OpenStack Swift user's domain name.
     * # CLI flag: -<prefix>.swift.user-domain-name
     * [user_domain_name: <string> | default = ""]
     *
     * # OpenStack Swift user's domain ID.
     * # CLI flag: -<prefix>.swift.user-domain-id
     * [user_domain_id: <string> | default = ""]
     *
     * # OpenStack Swift user ID.
     * # CLI flag: -<prefix>.swift.user-id
     * [user_id: <string> | default = ""]
     *
     * # OpenStack Swift API key.
     * # CLI flag: -<prefix>.swift.password
     * [password: <string> | default = ""]
     *
     * # OpenStack Swift user's domain ID.
     * # CLI flag: -<prefix>.swift.domain-id
     * [domain_id: <string> | default = ""]
     *
     * # OpenStack Swift user's domain name.
     * # CLI flag: -<prefix>.swift.domain-name
     * [domain_name: <string> | default = ""]
     *
     * # OpenStack Swift project ID (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.project-id
     * [project_id: <string> | default = ""]
     *
     * # OpenStack Swift project name (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.project-name
     * [project_name: <string> | default = ""]
     *
     * # ID of the OpenStack Swift project's domain (v3 auth only), only needed if it
     * # differs the from user domain.
     * # CLI flag: -<prefix>.swift.project-domain-id
     * [project_domain_id: <string> | default = ""]
     *
     * # Name of the OpenStack Swift project's domain (v3 auth only), only needed if it
     * # differs from the user domain.
     * # CLI flag: -<prefix>.swift.project-domain-name
     * [project_domain_name: <string> | default = ""]
     *
     * # OpenStack Swift Region to use (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.region-name
     * [region_name: <string> | default = ""]
     *
     * # Name of the OpenStack Swift container to put chunks in.
     * # CLI flag: -<prefix>.swift.container-name
     * [container_name: <string> | default = ""]
     *
     * # Max retries on requests error.
     * # CLI flag: -<prefix>.swift.max-retries
     * [max_retries: <int> | default = 3]
     *
     * # Time after which a connection attempt is aborted.
     * # CLI flag: -<prefix>.swift.connect-timeout
     * [connect_timeout: <duration> | default = 10s]
     *
     * # Time after which an idle request is aborted. The timeout watchdog is reset
     * # each time some data is received, so the timeout triggers after X time no data
     * # is received on a request.
     * # CLI flag: -<prefix>.swift.request-timeout
     * [request_timeout: <duration> | default = 5s]
     *
     * http:
     *   # Path to the CA certificates to validate server certificate against. If not
     *   # set, the host's root CA certificates are used.
     *   # CLI flag: -<prefix>.swift.http.tls-ca-path
     *   [tls_ca_path: <string> | default = ""]
     */
) {
}