package io.violabs.picard.domain.k8sResources.service.endpointSlice

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.endpoint.Endpoint
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointPort


data class EndpointSlice(
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val metadata: ObjectMetadata? = null,
    val endpoints: List<Endpoint>? = null,
    val ports: List<EndpointPort>? = null
) : K8sResource<EndpointSlice.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<EndpointSlice>() {
        private var endpoints: List<Endpoint>? = null
        private var ports: List<EndpointPort>? = null

        fun endpoints(scope: Endpoint.Group.() -> Unit) {
            endpoints = Endpoint.Group().apply(scope).endpoints()
        }

        fun ports(scope: EndpointPort.Group.() -> Unit) {
            ports = EndpointPort.Group().apply(scope).ports()
        }

        override fun build(): EndpointSlice {
            return EndpointSlice(
                metadata = metadata,
                endpoints = endpoints,
                ports = ports
            )
        }
    }

    class Group : K8sListResource.ItemGroup<EndpointSlice, Builder>(Builder()) {
        fun slice(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}
