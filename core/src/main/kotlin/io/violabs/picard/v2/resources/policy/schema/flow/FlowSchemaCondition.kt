package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

@GeneratedDsl(withListGroup = true)
data class FlowSchemaCondition(
    /**
     * lastTransitionTime is the last time the condition transitioned from
     * one status to another.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * message is a human-readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * conditions.reason (string)
     *
     * reason is a unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null,
    /**
     * status is the status of the condition. Can be True, False, Unknown. Required.
     */
    val status: BooleanType? = null,
    /**
     * conditions.type (string)
     *
     * type is the type of the condition. Required.
     */
    val type: String? = null
)
