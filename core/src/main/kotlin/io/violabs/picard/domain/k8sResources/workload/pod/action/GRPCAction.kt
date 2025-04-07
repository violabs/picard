package io.violabs.picard.domain.k8sResources.workload.pod.action

data class GRPCAction(
    val port: Int,
    val service: String? = null
)