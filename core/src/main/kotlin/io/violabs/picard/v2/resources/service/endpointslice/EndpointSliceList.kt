package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

/**
 * EndpointSliceList is a list of EndpointSlices.
 */
@GeneratedDsl
data class EndpointSliceList(
    @DefaultValue(
        "KAPIVersion.DiscoveryV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.DiscoveryV1,
    override val items: List<EndpointSlice>,
    override val metadata: ListMeta? = null
) : ServiceListResource<EndpointSliceList.Version, EndpointSlice> {
    interface Version : APIVersion
}
