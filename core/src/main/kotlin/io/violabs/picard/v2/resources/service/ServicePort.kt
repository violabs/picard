package io.violabs.picard.v2.resources.service

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class ServicePort(
    /**
     * The port that will be exposed by this service.
     */
    val port: Int,
    
    /**
     * Number or name of the port to access on the pods targeted by the service.
     */
    val targetPort: String? = null,
    
    /**
     * The IP protocol for this port. Supports "TCP", "UDP", and "SCTP". Default is TCP.
     */
    val protocol: String? = null,
    
    /**
     * The name of this port within the service. This must be a DNS_LABEL.
     */
    val name: String? = null,
    
    /**
     * The port on each node on which this service is exposed when type is NodePort or LoadBalancer.
     */
    val nodePort: Int? = null,
    
    /**
     * The application protocol for this port.
     */
    val appProtocol: String? = null
)