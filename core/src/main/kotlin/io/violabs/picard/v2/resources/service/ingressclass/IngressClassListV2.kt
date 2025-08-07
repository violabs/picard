package io.violabs.picard.v2.resources.service.ingressclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceListResource

/**
 * IngressClassList is a list of IngressClasss.
 */
@GeneratedDsl
data class IngressClassListV2(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<IngressClassV2>,
    override val metadata: ListMeta? = null
) : ServiceListResource<IngressClassListV2.Version, IngressClassV2> {
    interface Version : APIVersion
}
