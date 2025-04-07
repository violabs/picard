package io.violabs.picard.domain.k8sResources.cluster.event

data class EventSource(
    val component: String? = null,
    val host: String? = null
)