package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.common.DslBuilder

data class EventSource(
    val component: String? = null,
    val host: String? = null
) {
    class Builder : DslBuilder<EventSource> {
        var component: String? = null
        var host: String? = null

        override fun build(): EventSource {
            return EventSource(component, host)
        }
    }
}