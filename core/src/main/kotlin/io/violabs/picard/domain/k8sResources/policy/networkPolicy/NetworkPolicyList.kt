package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class NetworkPolicyList(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val items: List<NetworkPolicy>,
    override val metadata: ListMeta? = null
) : K8sListResource<NetworkPolicyList.Version, NetworkPolicy> {
    interface Version : APIVersion
}
