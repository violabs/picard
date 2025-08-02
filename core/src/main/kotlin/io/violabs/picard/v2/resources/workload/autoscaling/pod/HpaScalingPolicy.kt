package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * HPAScalingPolicy is a single policy which must hold true for a specified past interval.
 */
@GeneratedDsl(withListGroup = true)
data class HpaScalingPolicy(
    /**
     * type is used to specify the scaling policy.
     */
    val type: String,
    /**
     * value contains the amount of change which is permitted by the policy. It must be greater than zero
     */
    val value: Int,
    /**
     * periodSeconds specifies the window of time for which the policy should hold true. 
     * PeriodSeconds must be greater than zero and less than or equal to 1800 (30 min).
     */
    val periodSeconds: Int
)