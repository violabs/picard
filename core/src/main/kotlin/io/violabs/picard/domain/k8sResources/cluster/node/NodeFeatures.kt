package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DslBuilder

data class NodeFeatures(
    val supplementalGroupsPolicy: Boolean? = null
) {
    class Builder : DslBuilder<NodeFeatures> {
        private var supplementalGroupsPolicy: Boolean? = null

        fun supplementalGroupsPolicy(value: Boolean = true) {
            this.supplementalGroupsPolicy = value
        }

        override fun build(): NodeFeatures {
            return NodeFeatures(supplementalGroupsPolicy)
        }
    }
}