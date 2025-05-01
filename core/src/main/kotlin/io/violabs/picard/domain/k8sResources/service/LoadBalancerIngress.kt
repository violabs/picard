package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.BaseLoadBalancerIngress
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class LoadBalancerIngress(
    val hostname: String? = null,
    val ip: String? = null,
    val ipMode: String? = null,
    val ports: List<ServicePortStatus>? = null
) : BaseLoadBalancerIngress {
    class Builder : DSLBuilder<LoadBalancerIngress> {
        var hostname: String? = null
        var ip: String? = null
        var ipMode: String? = null
        private var ports: List<ServicePortStatus>? = null

        fun ports(scope: ServicePortStatus.Group.() -> Unit) {
            ports = ServicePortStatus.Group().apply(scope).ports()
        }

        override fun build(): LoadBalancerIngress {
            return LoadBalancerIngress(
                hostname = hostname,
                ip = ip,
                ipMode = ipMode,
                ports = ports
            )
        }
    }

class Group : BuilderGroup<LoadBalancerIngress, Builder>(Builder()) {
        fun ingresses(): List<LoadBalancerIngress>? = items()

        fun ingress(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}