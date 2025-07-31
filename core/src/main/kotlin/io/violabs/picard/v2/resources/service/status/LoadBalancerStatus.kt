package io.violabs.picard.v2.resources.service.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class LoadBalancerStatus(
    /**
     * Ingress is a list containing ingress points for the load-balancer.
     */
    val ingress: List<LoadBalancerIngress>? = null
)