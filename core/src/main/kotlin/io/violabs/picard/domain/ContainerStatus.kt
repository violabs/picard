package io.violabs.picard.domain

data class ContainerStatus(
    val containerId: String,
    val ready: Boolean
)