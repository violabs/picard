package io.violabs.picard.v2.resources.workload.daemon

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

/**
 * DaemonSetCondition describes the state of a DaemonSet at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class DaemonSetCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: BooleanType,
    /**
     * Type of DaemonSet condition.
     */
    val type: String,
    /**
     * Last time the condition transitioned from one status to another.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * A human readable message indicating details about the transition.
     */
    val message: String? = null,
    /**
     * The reason for the condition's last transition.
     */
    val reason: String? = null
)