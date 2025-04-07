package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.Operator

data class ScopedResourceSelectorRequirement(
    val operator: Operator,
    val scopeName: String,
    val values: List<String>? = null
)