package io.violabs.picard.domain.k8sResources.workload.nodeSelector

class NodeSelectorTermGroup {
    private val terms = mutableListOf<NodeSelectorTerm>()
    fun terms(): MutableList<NodeSelectorTerm> {
        return terms
    }

    fun term(scope: NodeSelectorTerm.Builder.() -> Unit) {
        terms.add(NodeSelectorTerm.Builder().apply(scope).build())
    }
}