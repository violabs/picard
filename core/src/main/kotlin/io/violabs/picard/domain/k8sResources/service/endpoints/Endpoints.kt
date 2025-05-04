package io.violabs.picard.domain.k8sResources.service.endpoints

import io.violabs.picard.common.ResourceDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointSubset


data class Endpoints(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val subsets: List<EndpointSubset>? = null
) : K8sResource<Endpoints.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<Endpoints>() {
        private var subsets: List<EndpointSubset>? = null

        fun subsets(scope: EndpointSubset.Group.() -> Unit) {
            subsets = EndpointSubset.Group().apply(scope).subsets()
        }

        override fun build(): Endpoints {
            return Endpoints(
                metadata = metadata,
                subsets = subsets
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Endpoints, Builder>(Builder()) {
        fun endpoints(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}
