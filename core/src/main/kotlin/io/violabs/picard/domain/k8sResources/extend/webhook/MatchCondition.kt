package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class MatchCondition(
    val expression: String,
    val name: String
) {
    class Builder : DslBuilder<MatchCondition> {
        var expression: String? = null
        var name: String? = null

        override fun build(): MatchCondition {
            return MatchCondition(
                expression = vRequireNotNull(this::expression),
                name = vRequireNotNull(this::name)
            )
        }
    }

    class Group : BuilderGroup<MatchCondition, Builder>(Builder()) {
        fun conditions(): List<MatchCondition>? = items()

        fun addMatchCondition(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}