package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.policy.IPBlock

data class NetworkPolicyPeer(
    val ipBlock: IPBlock? = null,
    val namespaceSelector: LabelSelector? = null,
    val podSelector: LabelSelector? = null
)