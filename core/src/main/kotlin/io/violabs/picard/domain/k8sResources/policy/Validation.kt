package io.violabs.picard.domain.k8sResources.policy

data class Validation(
    val expression: String,
    val message: String? = null,
    val messageExpression: String? = null,
    val reason: String? = null
)