package io.violabs.picard.domain.k8sResources.service.endpointSlice

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.Endpoint


data class EndpointSlice(
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val metadata: ObjectMetadata? = null,
    val endpoints: List<Endpoint>? = null,
    val ports: List<Endpoint.Port>? = null
) : K8sResource<EndpointSlice.Version> {
    interface Version : APIVersion
}
