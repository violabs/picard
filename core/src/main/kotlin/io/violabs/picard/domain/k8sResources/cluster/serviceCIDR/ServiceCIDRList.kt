package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

data class ServiceCIDRList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val items: List<ServiceCIDR>,
    override val metadata: ListMeta? = null
) : ClusterListResource<ServiceCIDRList.Version, ServiceCIDR> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ServiceCIDR,
        ServiceCIDR.Builder,
        ServiceCIDR.Group,
        ServiceCIDRList
        >(ServiceCIDR.Group()) {

        override fun build(): ServiceCIDRList {
            return ServiceCIDRList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
