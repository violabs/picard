package io.violabs.picard.domain.k8sResources.pod

data class ExecAction(
    val command: List<String>? = null
)