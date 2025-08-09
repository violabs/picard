package io.violabs.picard.v2.resources.workload.batch.job.policy.success

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SuccessPolicy describes when a Job can be declared as succeeded based on the success of some indexes.
 */
@GeneratedDsl(withListGroup = true)
data class SuccessPolicy(
    /**
     * rules represents the list of alternative rules for the declaring the
     * Jobs as successful before .status.succeeded >= .spec.completions.
     * Once any of the rules are met, the "SucceededCriteriaMet" condition is added,
     * and the lingering pods are removed. The terminal state for such a Job has the "Complete"
     * condition. Additionally, these rules are evaluated in order; Once the Job meets one of
     * the rules, other rules are ignored. At most 20 elements are allowed.
     */
    val rules: List<SuccessPolicyRule>
)