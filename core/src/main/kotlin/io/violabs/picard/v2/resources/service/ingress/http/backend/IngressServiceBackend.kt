package io.violabs.picard.v2.resources.service.ingress.http.backend

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressServiceBackend references a Kubernetes Service as a Backend.
 */
@GeneratedDsl
data class IngressServiceBackend(
    /**
     * name is the referenced service. The service must exist in the same namespace as the Ingress object.
     */
    val name: String,
    /**
     * port of the referenced service. A port name or port number is required for a IngressServiceBackend.
     */
    val port: ServiceBackendPort? = null
)