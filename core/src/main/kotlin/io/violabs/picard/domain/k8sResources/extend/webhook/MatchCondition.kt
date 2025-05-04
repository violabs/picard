package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class MatchCondition(
    val expression: String,
    val name: String
) {
    class Builder : DSLBuilder<MatchCondition> {
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

        fun condition(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}