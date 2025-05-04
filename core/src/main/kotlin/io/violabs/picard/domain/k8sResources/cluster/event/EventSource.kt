package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.common.DSLBuilder

data class EventSource(
    val component: String? = null,
    val host: String? = null
) {
    class Builder : DSLBuilder<EventSource> {
        var component: String? = null
        var host: String? = null

        override fun build(): EventSource {
            return EventSource(component, host)
        }
    }
}