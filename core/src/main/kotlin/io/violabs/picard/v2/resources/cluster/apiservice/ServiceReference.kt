package io.violabs.picard.v2.resources.cluster.apiservice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceReference holds a reference to Service.legacy.k8s.io
 */
@GeneratedDsl
data class ServiceReference(
    /**
     * Name is the name of the service
     */
    val name: String? = null,
    /**
     * Namespace is the namespace of the service
     */
    val namespace: String? = null,
    /**
     * If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility. 
     * port should be a valid port number (1-65535, inclusive).
     */
    val port: Int? = null
)