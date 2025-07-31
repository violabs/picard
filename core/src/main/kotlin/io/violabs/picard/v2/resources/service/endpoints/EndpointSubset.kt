package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class EndpointSubset(
    /**
     * IP addresses which offer the related ports that are marked as ready. 
     * These endpoints should be considered safe for load balancers and clients to utilize.
     */
    val addresses: List<EndpointAddress>? = null,
    
    /**
     * IP addresses which offer the related ports but are not currently marked as ready 
     * because they have not yet finished starting, have recently failed a readiness check, 
     * or have recently failed a liveness check.
     */
    val notReadyAddresses: List<EndpointAddress>? = null,
    
    /**
     * Port numbers available on the related IP addresses.
     */
    val ports: List<EndpointPort>? = null
)