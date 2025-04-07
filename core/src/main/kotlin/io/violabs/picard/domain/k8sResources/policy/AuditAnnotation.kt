package io.violabs.picard.domain.k8sResources.policy

data class AuditAnnotation(
    val key: String,
    val valueExpression: String
)