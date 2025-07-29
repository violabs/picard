package io.violabs.picard.v2.resources.extend.resource.webhook

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceReference holds a reference to Service.legacy.k8s.io
 */
@GeneratedDsl
data class ServiceReference(
    /**
     * name is the name of the service. Required
     */
    val name: String? = null,
    /**
     * namespace is the namespace of the service. Required
     */
    val namespace: String? = null,
    /**
     * path is an optional URL path at which the webhook will be contacted.
     */
    val path: String? = null,
    /**
     * port is an optional service port at which the webhook will be contacted. 
     * port should be a valid port number (1-65535, inclusive). Defaults to 443 for backward compatibility.
     */
    val port: Int? = null
)