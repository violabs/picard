package io.violabs.picard.domain.k8sResources.workload.pod.action

data class HttpHeader(
    val name: String,
    val value: String
)