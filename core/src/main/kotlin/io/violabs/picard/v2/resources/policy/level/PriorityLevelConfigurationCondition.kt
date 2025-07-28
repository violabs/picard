package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * PriorityLevelConfigurationCondition defines the condition of priority level.
 */
@GeneratedDsl(withListGroup = true)
data class PriorityLevelConfigurationCondition(
    /**
     * lastTransitionTime is the last time the condition transitioned from one status to another.
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * message is a human-readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * reason is a unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null,
    /**
     * status is the status of the condition. Can be True, False, Unknown. Required.
     */
    val status: String? = null,
    /**
     * type is the type of the condition. Required.
     */
    val type: String? = null
)