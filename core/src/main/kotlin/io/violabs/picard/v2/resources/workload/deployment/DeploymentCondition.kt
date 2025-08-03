package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * DeploymentCondition describes the state of a deployment at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class DeploymentCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * Type of deployment condition.
     */
    val type: String,
    /**
     * Last time the condition transitioned from one status to another.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * The last time this condition was updated.
     */
    val lastUpdateTime: LocalDateTime? = null,
    /**
     * A human readable message indicating details about the transition.
     */
    val message: String? = null,
    /**
     * The reason for the condition's last transition.
     */
    val reason: String? = null
)