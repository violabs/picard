package io.violabs.picard.v2.resources.service.ingress.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressLoadBalancerStatus represents the status of a load-balancer.
 */
@GeneratedDsl(withListGroup = true)
data class IngressLoadBalancerStatus(
    /**
     * ingress is a list containing ingress points for the load-balancer.
     */
    val ingress: List<IngressLoadBalancerIngress>? = null
)