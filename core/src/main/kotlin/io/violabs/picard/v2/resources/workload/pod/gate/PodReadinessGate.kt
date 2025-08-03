package io.violabs.picard.v2.resources.workload.pod.gate

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodReadinessGate contains the reference to a pod condition
 */
@GeneratedDsl(withListGroup = true)
data class PodReadinessGate(
    /**
     * ConditionType refers to a condition in the pod's condition list with matching type.
     */
    val conditionType: String
)