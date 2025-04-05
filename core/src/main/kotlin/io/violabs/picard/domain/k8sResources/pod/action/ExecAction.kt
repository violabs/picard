package io.violabs.picard.domain.k8sResources.pod.action

data class ExecAction(
    val command: List<String>? = null
)