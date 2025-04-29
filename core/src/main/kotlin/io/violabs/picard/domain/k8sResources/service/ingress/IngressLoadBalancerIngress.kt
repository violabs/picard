package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.domain.BaseLoadBalancerIngress
import io.violabs.picard.domain.DSLBuilder

data class IngressLoadBalancerIngress(
    val hostname: String? = null,
    val ip: String? = null,
    val ports: List<IngressPortStatus>? = null
) : BaseLoadBalancerIngress {
    class Builder : DSLBuilder<IngressLoadBalancerIngress> {
        var hostname: String? = null
        var ip: String? = null
        private var ports: List<IngressPortStatus>? = null

        fun ports(scope: IngressPortStatus.Group.() -> Unit) {
            ports = IngressPortStatus.Group().apply(scope).statuses()
        }

        override fun build(): IngressLoadBalancerIngress {
            return IngressLoadBalancerIngress(
                hostname = hostname,
                ip = ip,
                ports = ports
            )
        }
    }
}