package io.violabs.picard.v2.resources.workload.set.replica

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * ReplicaSetCondition describes the state of a replica set at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class ReplicaSetCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * Type of replica set condition.
     */
    val type: String,
    /**
     * The last time the condition transitioned from one status to another.
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