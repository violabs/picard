package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class EndpointPort(
    /**
     * The port number of the endpoint.
     */
    val port: Int,
    
    /**
     * The IP protocol for this port. Must be UDP, TCP, or SCTP. Default is TCP.
     */
    val protocol: String? = null,
    
    /**
     * The name of this port. This must match the 'name' field in the corresponding ServicePort. 
     * Must be a DNS_LABEL. Optional only if one port is defined.
     */
    val name: String? = null,
    
    /**
     * The application protocol for this port. This is used as a hint for implementations 
     * to offer richer behavior for protocols that they understand.
     */
    val appProtocol: String? = null
)