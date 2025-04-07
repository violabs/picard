package io.violabs.picard.domain.k8sResources.extend.webhook

data class MatchCondition(
    val expression: String,
    val name: String
)