package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceReference holds a reference to Service.legacy.k8s.io
 */
@GeneratedDsl
data class ServiceReference(
    /**
     * name is the name of the service. Required
     */
    val name: String,
    /**
     * namespace is the namespace of the service. Required
     */
    val namespace: String,
    /**
     * path is an optional URL path which will be sent in any request to this service.
     */
    val path: String? = null,
    /**
     * If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility.
     * port should be a valid port number (1-65535, inclusive).
     */
    val port: Int? = null
)