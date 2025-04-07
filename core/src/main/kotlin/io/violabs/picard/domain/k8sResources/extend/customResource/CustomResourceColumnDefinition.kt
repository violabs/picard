package io.violabs.picard.domain.k8sResources.extend.customResource

data class CustomResourceColumnDefinition(
    val jsonPath: String,
    val name: String,
    val type: String,
    val description: String? = null,
    val format: String? = null,
    val priority: Int? = null
)