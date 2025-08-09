package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

/**
 * JobCondition describes current state of a job.
 */
@GeneratedDsl(withListGroup = true)
data class JobCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: BooleanType,
    /**
     * Type of job condition, Complete or Failed.
     */
    val type: Type,
    /**
     * Last time the condition was checked.
     */
    val lastProbeTime: LocalDateTime? = null,
    /**
     * Last time the condition transit from one status to another.
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
) {
    enum class Type {
        Complete,
        Failed
    }
}