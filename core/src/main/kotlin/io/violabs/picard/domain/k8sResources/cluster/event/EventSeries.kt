package io.violabs.picard.domain.k8sResources.cluster.event

import java.time.Instant

data class EventSeries(
    val count: Int,
    val lastObservedTime: Instant
)
