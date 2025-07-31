package io.violabs.picard.v2.resources.service.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class PortStatus(
    /**
     * Port is the port number of the service port of which status is recorded here.
     */
    val port: Int,
    
    /**
     * Protocol is the protocol of the service port of which status is recorded here.
     * The supported values are: "TCP", "UDP", "SCTP"
     */
    val protocol: String,
    
    /**
     * Error is to record the problem with the service port.
     */
    val error: String? = null
)