package io.violabs.picard.domain.k8sResources.extend.json

data class ValidationRule(
    val rule: String,
    val fieldPath: String? = null,
    val message: String? = null,
    val messageExpression: String? = null,
    val optionalOldSelf: Boolean? = null,
    val reason: String? = null
)