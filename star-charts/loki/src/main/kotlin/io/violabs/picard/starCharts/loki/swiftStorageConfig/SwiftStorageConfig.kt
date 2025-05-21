package io.violabs.picard.starCharts.loki.swiftStorageConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL(
    withMapGroup = "SINGLE"
)
class SwiftStorageConfig(
    /**
     * # OpenStack Swift authentication API version. 0 to autodetect.
     * # CLI flag: -<prefix>.swift.auth-version
     * [auth_version: <int> | default = 0]
     */
    val authVersion: Int? = null,
    /**
     * # OpenStack Swift authentication URL
     * # CLI flag: -<prefix>.swift.auth-url
     * [auth_url: <string> | default = ""]
     */
    val authUrl: String? = null,
    /**
     * # Set this to true to use the internal OpenStack Swift endpoint URL
     * # CLI flag: -<prefix>.swift.internal
     * [internal: <boolean> | default = false]
     */
    val internal: Boolean? = null,
    /**
     * # OpenStack Swift username.
     * # CLI flag: -<prefix>.swift.username
     * [username: <string> | default = ""]
     */
    val username: String? = null,
    /**
     * # OpenStack Swift user's domain name.
     * # CLI flag: -<prefix>.swift.user-domain-name
     * [user_domain_name: <string> | default = ""]
     */
    val userDomainName: String? = null,
    /**
     * # OpenStack Swift user's domain ID.
     * # CLI flag: -<prefix>.swift.user-domain-id
     * [user_domain_id: <string> | default = ""]
     */
    val userDomainId: String? = null,
    /**
     * # OpenStack Swift user ID.
     * # CLI flag: -<prefix>.swift.user-id
     * [user_id: <string> | default = ""]
     */
    val userId: String? = null,
    /**
     * # OpenStack Swift API key.
     * # CLI flag: -<prefix>.swift.password
     * [password: <string> | default = ""]
     */
    val password: String? = null,
    /**
     * # OpenStack Swift user's domain ID.
     * # CLI flag: -<prefix>.swift.domain-id
     * [domain_id: <string> | default = ""]
     */
    val domainId: String? = null,
    /**
     * # OpenStack Swift user's domain name.
     * # CLI flag: -<prefix>.swift.domain-name
     * [domain_name: <string> | default = ""]
     */
    val domainName: String? = null,
    /**
     * # OpenStack Swift project ID (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.project-id
     * [project_id: <string> | default = ""]
     */
    val projectId: String? = null,
    /**
     * # OpenStack Swift project name (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.project-name
     * [project_name: <string> | default = ""]
     */
    val projectName: String? = null,
    /**
     * # ID of the OpenStack Swift project's domain (v3 auth only), only needed if it
     * # differs the from user domain.
     * # CLI flag: -<prefix>.swift.project-domain-id
     * [project_domain_id: <string> | default = ""]
     */
    val projectDomainId: String? = null,
    /**
     * # Name of the OpenStack Swift project's domain (v3 auth only), only needed if it
     * # differs from the user domain.
     * # CLI flag: -<prefix>.swift.project-domain-name
     * [project_domain_name: <string> | default = ""]
     */
    val projectDomainName: String? = null,
    /**
     * # OpenStack Swift Region to use (v2,v3 auth only).
     * # CLI flag: -<prefix>.swift.region-name
     * [region_name: <string> | default = ""]
     */
    val regionName: String? = null,
    /**
     * # Name of the OpenStack Swift container to put chunks in.
     * # CLI flag: -<prefix>.swift.container-name
     * [container_name: <string> | default = ""]
     */
    val containerName: String? = null,
    /**
     * # Max retries on requests error.
     * # CLI flag: -<prefix>.swift.max-retries
     * [max_retries: <int> | default = 3]
     */
    val maxRetries: Int? = null,
    /**
     * # Time after which a connection attempt is aborted.
     * # CLI flag: -<prefix>.swift.connect-timeout
     * [connect_timeout: <duration> | default = 10s]
     */
    val connectTimeout: Duration? = null,
    /**
     * # Time after which an idle request is aborted. The timeout watchdog is reset
     * # each time some data is received, so the timeout triggers after X time no data
     * # is received on a request.
     * # CLI flag: -<prefix>.swift.request-timeout
     * [request_timeout: <duration> | default = 5s]
     */
    val requestTimeout: Duration? = null,
    val http: Http? = null
)