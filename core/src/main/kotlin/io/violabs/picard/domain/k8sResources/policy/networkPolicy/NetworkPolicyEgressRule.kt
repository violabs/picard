package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class NetworkPolicyEgressRule(
    val to: List<NetworkPolicyPeer>? = null,
    val ports: List<NetworkPolicyPort>? = null
) {
    class Builder : DSLBuilder<NetworkPolicyEgressRule> {
        private var to: List<NetworkPolicyPeer>? = null
        private var ports: List<NetworkPolicyPort>? = null

        fun to(block: NetworkPolicyPeer.Group.() -> Unit) {
            this.to = NetworkPolicyPeer.Group().apply(block).peers()
        }

        fun ports(block: NetworkPolicyPort.Group.() -> Unit) {
            this.ports = NetworkPolicyPort.Group().apply(block).ports()
        }

        override fun build(): NetworkPolicyEgressRule {
            return NetworkPolicyEgressRule(
                to = to,
                ports = ports
            )
        }
    }

    class Group : BuilderGroup<NetworkPolicyEgressRule, Builder>(Builder()) {
        fun rules(): List<NetworkPolicyEgressRule>? = items()

        fun addNetworkPolicyEgressRule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}