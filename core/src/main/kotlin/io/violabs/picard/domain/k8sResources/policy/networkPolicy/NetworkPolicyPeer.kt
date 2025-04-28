package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.policy.IPBlock

data class NetworkPolicyPeer(
    val ipBlock: IPBlock? = null,
    val namespaceSelector: LabelSelector? = null,
    val podSelector: LabelSelector? = null
) {
    class Builder : DSLBuilder<NetworkPolicyPeer> {
        private var ipBlock: IPBlock? = null
        private var namespaceSelector: LabelSelector? = null
        private var podSelector: LabelSelector? = null

        fun ipBlock(block: IPBlock.Builder.() -> Unit) {
            this.ipBlock = IPBlock.Builder().apply(block).build()
        }

        fun namespaceSelector(block: LabelSelector.Builder.() -> Unit) {
            this.namespaceSelector = LabelSelector.Builder().apply(block).build()
        }

        fun podSelector(block: LabelSelector.Builder.() -> Unit) {
            this.podSelector = LabelSelector.Builder().apply(block).build()
        }

        override fun build(): NetworkPolicyPeer {
            return NetworkPolicyPeer(
                ipBlock = ipBlock,
                namespaceSelector = namespaceSelector,
                podSelector = podSelector
            )
        }
    }

    class Group : BuilderGroup<NetworkPolicyPeer, Builder>(Builder()) {
        fun peers(): List<NetworkPolicyPeer>? = items()

        fun peer(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}