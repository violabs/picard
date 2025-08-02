package io.violabs.picard.v2.resources.workload.stateful.set

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * StatefulSetCondition describes the state of a statefulset at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class StatefulSetCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * Type of statefulset condition.
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