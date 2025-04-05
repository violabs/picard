package io.violabs.picard.domain.k8sResources.pod

data class GRPCAction(
    val port: Int,
    val service: String? = null
)