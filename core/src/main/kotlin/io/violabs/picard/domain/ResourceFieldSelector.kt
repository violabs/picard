package io.violabs.picard.domain

import io.violabs.picard.domain.k8sResources.Quantity

data class ResourceFieldSelector(
    val resource: String,
    val containerName: String,
    val divisor: Quantity? = null
)