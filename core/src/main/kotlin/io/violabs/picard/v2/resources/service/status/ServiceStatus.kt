package io.violabs.picard.v2.resources.service.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BaseStatus

@GeneratedDsl(withListGroup = true)
data class ServiceStatus(
    /**
     * Current service state
     */
    val conditions: List<Condition>? = null,
    
    /**
     * LoadBalancer contains the current status of the load-balancer, if one is present.
     */
    val loadBalancer: LoadBalancerStatus? = null
) : BaseStatus