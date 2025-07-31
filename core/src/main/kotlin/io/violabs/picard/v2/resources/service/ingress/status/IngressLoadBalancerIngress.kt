package io.violabs.picard.v2.resources.service.ingress.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressLoadBalancerIngress represents the status of a load-balancer ingress point.
 */
@GeneratedDsl(withListGroup = true)
data class IngressLoadBalancerIngress(
    /**
     * hostname is set for load-balancer ingress points that are DNS based.
     */
    val hostname: String? = null,
    /**
     * ip is set for load-balancer ingress points that are IP based.
     */
    val ip: String? = null,
    /**
     * ports provides information about the ports exposed by this LoadBalancer.
     */
    val ports: List<IngressPortStatus>? = null
)