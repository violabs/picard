package io.violabs.picard.v2.resources.service.ingress.http.backend

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceBackendPort is the service port being referenced.
 */
@GeneratedDsl
data class ServiceBackendPort(
    /**
     * name is the name of the port on the Service. This is a mutually exclusive setting with "Number".
     */
    val name: String? = null,
    /**
     * number is the numerical port number (e.g. 80) on the Service. This is a mutually exclusive
     * setting with "Name".
     */
    val number: Int? = null
)