package io.violabs.picard.v2.resources.workload.batch.job.policy.failure

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodFailurePolicyOnPodConditionsPattern describes a pattern for matching an actual pod condition type.
 */
@GeneratedDsl(withListGroup = true)
data class PodFailurePolicyOnPodConditionsPattern(
    /**
     * Specifies the required Pod condition status. To match a pod condition it is required
     * that the specified status equals the pod condition status. Defaults to True.
     */
    val status: String,
    /**
     * Specifies the required Pod condition type. To match a pod condition it is required that
     * specified type equals the pod condition type.
     */
    val type: String
)