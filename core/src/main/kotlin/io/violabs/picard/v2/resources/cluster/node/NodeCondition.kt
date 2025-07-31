package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * NodeCondition contains condition information for a node.
 */
@GeneratedDsl(withListGroup = true)
data class NodeCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * Type of node condition.
     */
    val type: String,
    /**
     * Last time we got an update on a given condition.
     *
     * Time is a wrapper around time.Time which supports correct marshaling
     * to YAML and JSON. Wrappers are provided for many of the factory methods
     * that the time package offers.
     */
    val lastHeartbeatTime: LocalDateTime? = null,
    /**
     * Last time the condition transit from one status to another.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML
     * and JSON. Wrappers are provided for many of the factory methods that the
     * time package offers.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * Human readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * (brief) reason for the condition's last transition.
     */
    val reason: String? = null
)