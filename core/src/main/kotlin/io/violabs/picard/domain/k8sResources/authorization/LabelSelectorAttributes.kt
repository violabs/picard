package io.violabs.picard.domain.k8sResources.authorization

data class LabelSelectorAttributes(
    val rawSelector: String? = null,
    val requirements: List<LabelSelectorRequirement>? = null
)