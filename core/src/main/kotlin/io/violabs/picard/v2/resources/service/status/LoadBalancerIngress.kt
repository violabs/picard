package io.violabs.picard.v2.resources.service.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class LoadBalancerIngress(
    /**
     * Hostname is set for load-balancer ingress points that are DNS based.
     */
    val hostname: String? = null,
    
    /**
     * IP is set for load-balancer ingress points that are IP based.
     */
    val ip: String? = null,
    
    /**
     * IPMode specifies how the load-balancer IP behaves.
     */
    val ipMode: String? = null,
    
    /**
     * Ports is a list of records of service ports.
     */
    val ports: List<PortStatus>? = null
)