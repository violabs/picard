package io.violabs.picard.v2.resources.service.ingress.http.backend

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressBackend describes all endpoints for a given service and port.
 */
@GeneratedDsl
data class IngressBackend(
    /**
     * resource is an ObjectRef to another Kubernetes resource in the namespace of the Ingress
     * object. If resource is specified, a service.Name and service.Port must not be specified.
     * This is a mutually exclusive setting with "Service".
     */
    val resource: TypedLocalObjectReference? = null,
    /**
     * service references a service as a backend. This is a mutually exclusive setting with "Resource".
     */
    val service: IngressServiceBackend? = null
)