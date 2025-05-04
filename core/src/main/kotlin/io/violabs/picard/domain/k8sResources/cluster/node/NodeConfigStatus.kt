package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DSLBuilder

data class NodeConfigStatus(
    val active: NodeConfigSource? = null,
    val assigned: NodeConfigSource? = null,
    val error: String? = null,
    val lastKnownGood: NodeConfigSource? = null,
) {
    class Builder : DSLBuilder<NodeConfigStatus> {
        private var active: NodeConfigSource? = null
        private var assigned: NodeConfigSource? = null
        var error: String? = null
        private var lastKnownGood: NodeConfigSource? = null

        fun active(scope: NodeConfigSource.Builder.() -> Unit) {
            active = NodeConfigSource.Builder().apply(scope).build()
        }

        fun assigned(scope: NodeConfigSource.Builder.() -> Unit) {
            assigned = NodeConfigSource.Builder().apply(scope).build()
        }

        fun lastKnownGood(scope: NodeConfigSource.Builder.() -> Unit) {
            lastKnownGood = NodeConfigSource.Builder().apply(scope).build()
        }

        override fun build(): NodeConfigStatus {
            return NodeConfigStatus(
                active = active,
                assigned = assigned,
                error = error,
                lastKnownGood = lastKnownGood,
            )
        }
    }
}