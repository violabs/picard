package io.violabs.picard.domain.k8sResources.policy

data class ExpressionWarning(
    val fieldRef: String,
    val warning: String
)