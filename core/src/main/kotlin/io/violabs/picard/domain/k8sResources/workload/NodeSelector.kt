package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.NodeSelectorRequirement

data class NodeSelector(
    val nodeSelectorTerms: List<Term>
) : BaseNodeSelector {
    data class Term(
        val matchExpression: List<NodeSelectorRequirement>? = null,
        val matchFields: List<NodeSelectorRequirement>? = null
    )
}