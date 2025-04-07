package io.violabs.picard.domain.k8sResources.policy

data class TypeChecking(
    val expressionWarnings: List<ExpressionWarning>? = null
)