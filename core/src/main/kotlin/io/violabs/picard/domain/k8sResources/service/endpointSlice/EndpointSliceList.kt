package io.violabs.picard.domain.k8sResources.service.endpointSlice

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class EndpointSliceList(
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val items: List<EndpointSlice>,
    override val metadata: ListMeta? = null
) : K8sListResource<EndpointSliceList.Version, EndpointSlice> {
    interface Version : APIVersion
    
    class Builder : ResourceListDSLBuilder<
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