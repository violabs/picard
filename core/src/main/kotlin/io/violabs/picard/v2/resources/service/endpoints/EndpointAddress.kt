package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectReference

@GeneratedDsl(withListGroup = true)
data class EndpointAddress(
    /**
     * The IP of this endpoint. May not be loopback (127.0.0.0/8 or ::1), 
     * link-local (169.254.0.0/16 or fe80::/10), or link-local multicast 
     * (224.0.0.0/24 or ff02::/16).
     */
    val ip: String,
    
    /**
     * The Hostname of this endpoint
     */
    val hostname: String? = null,
    
    /**
     * Optional: Node hosting this endpoint. This can be used to determine endpoints local to a node.
     */
    val nodeName: String? = null,
    
    /**
     * Reference to object providing the endpoint.
     */
    val targetRef: ObjectReference? = null
)