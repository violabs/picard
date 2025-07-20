package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class NodeAddress(
    val address: String,
    val type: String
) {
    class Builder : DslBuilder<NodeAddress> {
        var address: String? = null
        var type: String? = null

        override fun build(): NodeAddress {
            return NodeAddress(
                address = vRequireNotNull(this::address),
                type = vRequireNotNull(this::type)
            )
        }
    }

    class Group : BuilderGroup<NodeAddress, Builder>(Builder()) {
        fun addresses(): List<NodeAddress>? = items()

        fun addNodeAddress(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}