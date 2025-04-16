package io.violabs.picard.domain.k8sResources.workload.nodeSelector

import io.violabs.picard.domain.BaseNodeSelector
import io.violabs.picard.domain.DSLBuilder

data class NodeSelector(
    val nodeSelectorTerms: List<NodeSelectorTerm>
) : BaseNodeSelector {

    class Builder : DSLBuilder<NodeSelector> {
        private var _nodeSelectorTerms: List<NodeSelectorTerm>? = null
        override fun build(): NodeSelector {
            return NodeSelector(
                nodeSelectorTerms = requireNotNull(_nodeSelectorTerms)
            )
        }
    }
}