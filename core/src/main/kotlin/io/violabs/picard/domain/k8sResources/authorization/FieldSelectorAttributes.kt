package io.violabs.picard.domain.k8sResources.authorization

data class FieldSelectorAttributes(
    val rawSelector: String? = null,
    val resourceAttributes: List<FieldSelectorRequirement>? = null
)
