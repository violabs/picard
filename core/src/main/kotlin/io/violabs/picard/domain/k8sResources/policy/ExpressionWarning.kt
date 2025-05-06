package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class ExpressionWarning(
    val fieldRef: String,
    val warning: String
) {
    class Builder : DSLBuilder<ExpressionWarning> {
        var fieldRef: String? = null
        var warning: String? = null

        override fun build(): ExpressionWarning {
            return ExpressionWarning(
                fieldRef = vRequireNotNull(this::fieldRef),
                warning = vRequireNotNull(this::warning)
            )
        }
    }

    class Group : BuilderGroup<ExpressionWarning, Builder>(Builder()) {
        fun warnings(): List<ExpressionWarning>? = items()

        fun addExpressionWarning(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}