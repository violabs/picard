package io.violabs.picard.domain.k8sResources.service.endpointSlice

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.service.endpointslice.EndpointSliceListV2"))
data class EndpointSliceList(
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val items: List<EndpointSlice>,
    override val metadata: ListMeta? = null
) : ServiceListResource<EndpointSliceList.Version, EndpointSlice> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        EndpointSlice,
        EndpointSlice.Builder,
        EndpointSlice.Group,
        EndpointSliceList
        >(EndpointSlice.Group()) {

        override fun build(): EndpointSliceList {
            return EndpointSliceList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}