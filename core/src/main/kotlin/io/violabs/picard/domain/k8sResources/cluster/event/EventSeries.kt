package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder
import java.time.Instant

data class EventSeries(
    val count: Int,
    val lastObservedTime: Instant
) {
    class Builder : DslBuilder<EventSeries> {
        var count: Int? = null
        var lastObservedTime: Instant? = null

        override fun build(): EventSeries {
            return EventSeries(
                count = vRequireNotNull(this::count),
                lastObservedTime = vRequireNotNull(this::lastObservedTime)
            )
        }
    }
}
