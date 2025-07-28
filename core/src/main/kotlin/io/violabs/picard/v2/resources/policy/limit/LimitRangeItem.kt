package io.violabs.picard.v2.resources.policy.limit

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * LimitRangeItem defines a min/max usage limit for any resource that matches on kind.
 */
@GeneratedDsl(withListGroup = true)
data class LimitRangeItem(
    /**
     * Type of resource that this limit applies to.
     */
    val type: String,
    /**
     * Default resource requirement limit value by resource name if resource limit is omitted.
     */
    val default: Map<String, Quantity>? = null,
    /**
     * DefaultRequest is the default resource requirement request value by resource name if resource request is omitted.
     */
    val defaultRequest: Map<String, Quantity>? = null,
    /**
     * Max usage constraints on this kind by resource name.
     */
    val max: Map<String, Quantity>? = null,
    /**
     * MaxLimitRequestRatio if specified, the named resource must have a request and limit that are both non-zero 
     * where limit divided by request is less than or equal to the enumerated value; this represents the max burst for the named resource.
     */
    val maxLimitRequestRatio: Map<String, Quantity>? = null,
    /**
     * Min usage constraints on this kind by resource name.
     */
    val min: Map<String, Quantity>? = null
)