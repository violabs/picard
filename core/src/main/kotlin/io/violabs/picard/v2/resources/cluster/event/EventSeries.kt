package io.violabs.picard.v2.resources.cluster.event

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.Instant

/**
 * EventSeries contains information on series of events, i.e. thing that was/is happening continuously for some time.
 */
@GeneratedDsl
data class EventSeries(
    /**
     * Number of occurrences in this series up to the last heartbeat time.
     */
    val count: Int,
    /**
     * Time when last Event from the series was seen before last heartbeat.
     */
    val lastObservedTime: Instant
)