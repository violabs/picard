package io.violabs.picard.domain.k8sResources.service.endpoints

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.endpoint.EndpointSubset
import io.violabs.picard.domain.manifest.ServiceResource


@Deprecated("Use EndpointsV2", ReplaceWith("EndpointsV2", "io.violabs.picard.v2.resources.service.endpoints.EndpointsV2"))
data class Endpoints(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val subsets: List<EndpointSubset>? = null
) : ServiceResource<Endpoints.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<Endpoints>() {
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
        fun endpointsItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}
