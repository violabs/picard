package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * The weights of all of the matched WeightedPodAffinityTerm fields are added
 * per-node to find the most preferred node(s)
 */
@GeneratedDsl(withListGroup = true)
data class WeightedPodAffinityTerm(
    /**
     * weight associated with matching the corresponding podAffinityTerm, in the range 1-100.
     */
    val weight: Int,
    /**
     * Required. A pod affinity term, associated with the corresponding weight.
     */
    val podAffinityTerm: PodAffinityTerm
)