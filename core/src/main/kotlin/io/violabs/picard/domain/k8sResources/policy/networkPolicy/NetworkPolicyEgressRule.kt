package io.violabs.picard.domain.k8sResources.policy.networkPolicy

data class NetworkPolicyEgressRule(
    val to: List<NetworkPolicyPeer>? = null,
    val ports: List<NetworkPolicyPort>? = null
)