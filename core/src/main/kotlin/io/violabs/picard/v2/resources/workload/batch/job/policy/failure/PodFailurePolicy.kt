package io.violabs.picard.v2.resources.workload.batch.job.policy.failure

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodFailurePolicy describes how failed pods influence the backoffLimit.
 */
@GeneratedDsl(withListGroup = true)
data class PodFailurePolicy(
    /**
     * A list of pod failure policy rules. The rules are evaluated in order.
     * Once a rule matches a Pod failure, the remaining of the rules are ignored.
     * When no rule matches the Pod failure, the default handling applies - the
     * counter of pod failures is incremented and it is checked against the backoffLimit.
     * At most 20 elements are allowed.
     */
    val rules: List<PodFailurePolicyRule>
)