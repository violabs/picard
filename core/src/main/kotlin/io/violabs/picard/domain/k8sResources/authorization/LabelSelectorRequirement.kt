package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.Operator

data class LabelSelectorRequirement(
    val key: String,
    val operator: Operator,
    val values: List<String>? = null
)
