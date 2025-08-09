package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

/**
 * HorizontalPodAutoscalerCondition describes the state of a HorizontalPodAutoscaler at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class HorizontalPodAutoscalerCondition(
    /**
     * status is the status of the condition (True, False, Unknown)
     */
    val status: BooleanType,
    /**
     * type describes the current condition
     */
    val type: String,
    /**
     * lastTransitionTime is the last time the condition transitioned from one status to another
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * message is a human-readable explanation containing details about the transition
     */
    val message: String? = null,
    /**
     * reason is the reason for the condition's last transition.
     */
    val reason: String? = null
)