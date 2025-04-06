package io.violabs.picard.domain.k8sResources.workload

data class CrossVersionObjectReference(
    val kind: String,
    val name: String,
    val apiVersion: String? = null
)