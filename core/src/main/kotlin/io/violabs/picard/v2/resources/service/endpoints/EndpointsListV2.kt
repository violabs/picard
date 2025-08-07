package io.violabs.picard.v2.resources.service.endpoints

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

/**
 * EndpointsList is a list of Endpointss.
 */
@GeneratedDsl
data class EndpointsListV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<EndpointsV2>,
    override val metadata: ListMeta? = null
) : ServiceListResource<EndpointsListV2.Version, EndpointsV2> {
    interface Version : APIVersion
}
