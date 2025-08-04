package io.violabs.picard.v2.resources.workload.pod.container.action

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.pod.container.action.HttpHeader

/**
 * HTTPGetAction describes an action based on HTTP Get requests.
 */
@GeneratedDsl
data class HttpGetAction(
    /**
     * Path to access on the HTTP server.
     */
    val path: String? = null,
    /**
     * Name or number of the port to access on the container.
     * Number must be in the range 1 to 65535.
     * Name must be an IANA_SVC_NAME.
     */
    val port: String? = null,
    /**
     * Host name to connect to, defaults to the pod IP. You probably want to set
     * "Host" in httpHeaders instead.
     */
    val host: String? = null,
    /**
     * Scheme to use for connecting to the host.
     * Defaults to HTTP.
     */
    val scheme: String? = null,
    /**
     * Custom headers to set in the request. HTTP allows repeated headers.
     */
    val httpHeaders: List<HttpHeader>? = null
)