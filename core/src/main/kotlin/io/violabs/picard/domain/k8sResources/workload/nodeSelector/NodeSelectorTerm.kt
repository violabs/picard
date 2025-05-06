package io.violabs.picard.domain.k8sResources.workload.nodeSelector

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.NodeSelectorRequirement

data class NodeSelectorTerm(
    val matchExpression: List<NodeSelectorRequirement>? = null,
    val matchFields: List<NodeSelectorRequirement>? = null
) {
    class Builder : DSLBuilder<NodeSelectorTerm> {
        private var _matchExpression: List<NodeSelectorRequirement>? = null
        private var _matchFields: List<NodeSelectorRequirement>? = null

        fun matchExpressions(scope: NodeSelectorRequirement.Group.() -> Unit) {
            _matchExpression = NodeSelectorRequirement.Group().apply(scope).requirements()
        }

        fun matchFields(scope: NodeSelectorRequirement.Group.() -> Unit) {
            _matchFields = NodeSelectorRequirement.Group().apply(scope).requirements()
        }

        override fun build(): NodeSelectorTerm {
            return NodeSelectorTerm(
                matchExpression = _matchExpression,
                matchFields = _matchFields
            )
        }
    }

    class Group : BuilderGroup<NodeSelectorTerm, Builder>(Builder()) {
        fun terms(): List<NodeSelectorTerm>? = items()

        fun term(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}