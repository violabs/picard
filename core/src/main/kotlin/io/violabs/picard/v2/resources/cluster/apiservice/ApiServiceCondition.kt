package io.violabs.picard.v2.resources.cluster.apiservice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * APIServiceCondition describes the state of an APIService at a particular point
 */
@GeneratedDsl(withListGroup = true)
data class ApiServiceCondition(
    /**
     * Status is the status of the condition. Can be True, False, Unknown.
     */
    val status: String,
    /**
     * Type is the type of the condition.
     */
    val type: String,
    /**
     * Last time the condition transitioned from one status to another.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * Human-readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * Unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null
)