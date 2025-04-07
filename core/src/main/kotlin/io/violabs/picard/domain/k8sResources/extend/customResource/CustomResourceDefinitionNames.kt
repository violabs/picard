package io.violabs.picard.domain.k8sResources.extend.customResource

data class CustomResourceDefinitionNames(
    val kind: String,
    val plural: String,
    val categories: List<String>? = null,
    val listKind: String? = null,
    val shortNames: List<String>? = null,
    val singular: String? = null
)