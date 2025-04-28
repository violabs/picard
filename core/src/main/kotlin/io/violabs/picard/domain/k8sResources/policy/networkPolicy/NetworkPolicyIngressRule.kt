package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class NetworkPolicyIngressRule(
    val from: List<NetworkPolicyPeer>? = null,
    val ports: List<NetworkPolicyPort>? = null,
    val egress: List<NetworkPolicyEgressRule>? = null
) {
    class Builder : DSLBuilder<NetworkPolicyIngressRule> {
        private var from: List<NetworkPolicyPeer>? = null
        private var ports: List<NetworkPolicyPort>? = null
        private var egress: List<NetworkPolicyEgressRule>? = null

        fun from(block: NetworkPolicyPeer.Group.() -> Unit) {
            this.from = NetworkPolicyPeer.Group().apply(block).peers()
        }

        fun ports(block: NetworkPolicyPort.Group.() -> Unit) {
            this.ports = NetworkPolicyPort.Group().apply(block).ports()
        }

        fun egress(block: NetworkPolicyEgressRule.Group.() -> Unit) {
            this.egress = NetworkPolicyEgressRule.Group().apply(block).rules()
        }

        override fun build(): NetworkPolicyIngressRule {
            return NetworkPolicyIngressRule(
                from = from,
                ports = ports,
                egress = egress
            )
        }
    }

    class Group : BuilderGroup<NetworkPolicyIngressRule, Builder>(Builder()) {
        fun rules(): List<NetworkPolicyIngressRule>? = items()

        fun rule(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}