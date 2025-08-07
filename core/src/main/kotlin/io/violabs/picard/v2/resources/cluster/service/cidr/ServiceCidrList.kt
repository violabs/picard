package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.ClusterListResource

@GeneratedDsl
data class ServiceCidrList(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<ServiceCidr>,
    override val metadata: ListMeta? = null
) : ClusterListResource<ServiceCidrList.Version, ServiceCidr> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "ServiceCIDRList"
    }
}
