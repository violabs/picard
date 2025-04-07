package io.violabs.picard.domain.k8sResources.policy.networkPolicy

data class NetworkPolicyIngressRule(
    val from: List<NetworkPolicyPeer>? = null,
    val ports: List<NetworkPolicyPort>? = null,
    val egress: List<NetworkPolicyEgressRule>? = null
)