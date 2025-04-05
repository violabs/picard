package io.violabs.picard.domain.k8sResources.workload.pod.action

data class ExecAction(
    val command: List<String>? = null
)