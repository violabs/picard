package io.violabs.picard.domain

object TopologySelector {
    data class Term(
        val matchLabelExpressions: List<LabelRequirement>? = null
    ) {
        class Builder : DSLBuilder<Term> {
            private var matchLabelExpressions: List<LabelRequirement>? = null

            fun matchLabelExpressions(scope: LabelRequirement.Group.() -> Unit) {
                matchLabelExpressions = LabelRequirement.Group().apply(scope).requirements()
            }

            override fun build(): Term {
                return Term(
                    matchLabelExpressions = matchLabelExpressions
                )
            }
        }

        class Group : BuilderGroup<Term, Builder>(Builder()) {
            fun terms(): List<Term>? = items()

            fun term(scope: Builder.() -> Unit) {
                add(scope)
            }
        }
    }

    data class LabelRequirement(val key: String, val values: List<String>) {
        class Builder : DSLBuilder<LabelRequirement> {
            var key: String? = null
            private var values: List<String>? = null

            fun values(vararg values: String) {
                this.values = values.toList()
            }

            override fun build(): LabelRequirement {
                return LabelRequirement(
                    key = requireNotNull(key),
                    values = requireNotNull(values)
                )
            }
        }

        class Group : BuilderGroup<LabelRequirement, Builder>(Builder()) {
            fun requirements(): List<LabelRequirement>? = items()

            fun requirement(scope: Builder.() -> Unit) {
                add(scope)
            }
        }
    }
}