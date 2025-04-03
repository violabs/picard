package io.violabs.picard.domain

data class NodeSelectorRequirement(
    val key: String,
    val operator: String,
    val values: List<String>? = null
)