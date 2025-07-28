package io.violabs.picard.v2.resources.policy.limit

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * LimitRangeSpec defines a min/max usage limit for resources that match on kind.
 */
@GeneratedDsl
data class LimitRangeSpec(
    /**
     * Limits is the list of LimitRangeItem objects that are enforced.
     */
    val limits: List<LimitRangeItem>
)