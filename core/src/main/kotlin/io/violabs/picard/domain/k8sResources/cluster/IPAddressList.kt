package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class IPAddressList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val items: List<IPAddress>,
    override val metadata: ListMeta? = null
) : K8sListResource<IPAddressList.Version, IPAddress> {
    interface Version : APIVersion
}
