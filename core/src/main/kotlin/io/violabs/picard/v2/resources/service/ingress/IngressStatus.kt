package io.violabs.picard.v2.resources.service.ingress

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.service.ingress.status.IngressLoadBalancerStatus

/**
 * IngressStatus describe the current state of the Ingress.
 */
@GeneratedDsl
data class IngressStatus(
    /**
     * loadBalancer contains the current status of the load-balancer.
     */
    val loadBalancer: IngressLoadBalancerStatus? = null
)