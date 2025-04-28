package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.DSLBuilder

data class TypeChecking(
    val expressionWarnings: List<ExpressionWarning>? = null
) {
    class Builder : DSLBuilder<TypeChecking> {
        private var expressionWarnings: List<ExpressionWarning>? = null

        fun expressionWarnings(scope: ExpressionWarning.Group.() -> Unit) {
            expressionWarnings = ExpressionWarning.Group().apply(scope).warnings()
        }

        override fun build(): TypeChecking {
            return TypeChecking(
                expressionWarnings = expressionWarnings
            )
        }
    }
}