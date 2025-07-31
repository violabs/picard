package io.violabs.picard.v2.resources.service.ingress

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressTLS describes the transport layer security associated with an ingress.
 */
@GeneratedDsl(withListGroup = true)
data class IngressTLS(
    /**
     * hosts is a list of hosts included in the TLS certificate. The values in this list must match 
     * the name/s used in the tlsSecret. Defaults to the wildcard host setting for the loadbalancer 
     * controller fulfilling this Ingress, if left unspecified.
     */
    val hosts: List<String>? = null,
    /**
     * secretName is the name of the secret used to terminate TLS traffic on port 443. Field is left 
     * optional to allow TLS routing based on SNI hostname alone. If the SNI host in a listener conflicts 
     * with the "Host" header field used by an IngressRule, the SNI host is used for termination and 
     * value of the "Host" header is used for routing.
     */
    val secretName: String? = null
)