package io.violabs.picard.domain.k8sResources.policy

data class Variable(
    val expression: String,
    val name: String
)