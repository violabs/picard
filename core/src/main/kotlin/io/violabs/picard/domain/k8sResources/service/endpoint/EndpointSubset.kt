package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class EndpointSubset(
    val addresses: List<EndpointAddress>? = null,
    val notReadyAddresses: List<EndpointAddress>? = null,
    val ports: List<EndpointPort>? = null
) {
    class Builder : DSLBuilder<EndpointSubset> {
        private var addresses: List<EndpointAddress>? = null
        private var notReadyAddresses: List<EndpointAddress>? = null
        private var ports: List<EndpointPort>? = null

        fun addresses(scope: EndpointAddress.Group.() -> Unit) {
            addresses = EndpointAddress.Group().apply(scope).addresses()
        }

        fun notReadyAddresses(scope: EndpointAddress.Group.() -> Unit) {
            notReadyAddresses = EndpointAddress.Group().apply(scope).addresses()
        }

        fun ports(scope: EndpointPort.Group.() -> Unit) {
            ports = EndpointPort.Group().apply(scope).ports()
        }

        override fun build(): EndpointSubset {
            return EndpointSubset(
                addresses = addresses,
                notReadyAddresses = notReadyAddresses,
                ports = ports
            )
        }
    }

    class Group : BuilderGroup<EndpointSubset, Builder>(Builder()) {
        fun subsets(): List<EndpointSubset>? = items()

        fun subset(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}