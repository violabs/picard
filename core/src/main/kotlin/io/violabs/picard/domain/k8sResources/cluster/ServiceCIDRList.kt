package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ServiceCIDRList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val items: List<ServiceCIDR>,
    override val metadata: ListMeta? = null
) : K8sListResource<ServiceCIDRList.Version, ServiceCIDR> {
    interface Version : APIVersion
}
