package io.violabs.picard.domain.k8sResources.service.endpointSlice

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.endpoint.Endpoint
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointPort
import io.violabs.picard.domain.manifest.ServiceResource


data class EndpointSlice(
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val metadata: ObjectMetadata? = null,
    val endpoints: List<Endpoint>? = null,
    val ports: List<EndpointPort>? = null
) : ServiceResource<EndpointSlice.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<EndpointSlice>() {
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
        fun endpointSliceItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}
