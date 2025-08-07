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
data class IngressClassList(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<IngressClass>,
    override val metadata: ListMeta? = null
) : ServiceListResource<IngressClassList.Version, IngressClass> {
    interface Version : APIVersion
}
