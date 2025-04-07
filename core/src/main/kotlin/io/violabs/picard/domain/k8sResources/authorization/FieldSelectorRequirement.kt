package io.violabs.picard.domain.k8sResources.authorization

data class FieldSelectorRequirement(
    val key: String,
    val requirements: String,
    val values: List<String>? = null
)
