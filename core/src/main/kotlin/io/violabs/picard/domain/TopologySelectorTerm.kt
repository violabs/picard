package io.violabs.picard.domain

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class TopologySelectorTerm(
    val matchLabelExpressions: List<TopologySelectorLabelRequirement>? = null
) {
    class Builder : DSLBuilder<TopologySelectorTerm> {
        private var matchLabelExpressions: List<TopologySelectorLabelRequirement>? = null

        fun matchLabelExpressions(scope: TopologySelectorLabelRequirement.Group.() -> Unit) {
            matchLabelExpressions = TopologySelectorLabelRequirement.Group().apply(scope).requirements()
        }

        override fun build(): TopologySelectorTerm {
            return TopologySelectorTerm(
                matchLabelExpressions = matchLabelExpressions
            )
        }
    }

    class Group : BuilderGroup<TopologySelectorTerm, Builder>(Builder()) {
        fun terms(): List<TopologySelectorTerm>? = items()

        fun addTopologySelectorTerm(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}