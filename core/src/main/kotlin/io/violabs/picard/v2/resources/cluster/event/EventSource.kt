package io.violabs.picard.v2.resources.cluster.event

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EventSource contains information for an event.
 * 
 * This is deprecated and used for backward compatibility with core.v1 Event type.
 */
@GeneratedDsl
data class EventSource(
    /**
     * Component from which the event is generated.
     */
    val component: String? = null,
    /**
     * Node name on which the event is generated.
     */
    val host: String? = null
)