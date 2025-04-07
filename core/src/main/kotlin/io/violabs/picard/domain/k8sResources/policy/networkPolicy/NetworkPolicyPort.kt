package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol

data class NetworkPolicyPort(
    val port: IntOrString? = null,
    val endPort: Int? = null,
    val protocol: Protocol? = null
)