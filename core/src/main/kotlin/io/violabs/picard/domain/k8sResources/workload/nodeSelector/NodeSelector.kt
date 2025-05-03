package io.violabs.picard.domain.k8sResources.workload.nodeSelector

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BaseNodeSelector
import io.violabs.picard.domain.DSLBuilder

data class NodeSelector(
    val nodeSelectorTerms: List<NodeSelectorTerm>
) : BaseNodeSelector {

    class Builder : DSLBuilder<NodeSelector> {
        private var nodeSelectorTerms: List<NodeSelectorTerm>? = null

        fun terms(scope: NodeSelectorTerm.Group.() -> Unit) {
            this.nodeSelectorTerms = NodeSelectorTerm.Group().apply(scope).terms()
        }

        override fun build(): NodeSelector {
            return NodeSelector(
                nodeSelectorTerms = vRequireNotEmpty(this::nodeSelectorTerms)
            )
        }
    }
}