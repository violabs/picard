package io.violabs.picard.v2.resources.policy.limitRange

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Specification for a LimitRange.
 */
@GeneratedDsl
data class LimitRangeSpec(
    /**
     * List of limits that apply to resources.
     */
    val limits: List<LimitRangeItem>,
)
