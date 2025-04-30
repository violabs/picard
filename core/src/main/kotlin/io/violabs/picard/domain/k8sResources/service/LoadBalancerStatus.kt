package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.DSLBuilder

data class LoadBalancerStatus(
    val ingress: List<LoadBalancerIngress>? = null
) {

    class Builder : DSLBuilder<LoadBalancerStatus> {
        private var ingress: List<LoadBalancerIngress>? = null

        fun ingresses(scope: LoadBalancerIngress.Group.() -> Unit) {
            ingress = LoadBalancerIngress.Group().apply(scope).ingresses()
        }

        override fun build(): LoadBalancerStatus {
            return LoadBalancerStatus(
                ingress = ingress
            )
        }
    }
}