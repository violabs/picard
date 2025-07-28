package io.violabs.picard.v2.resources.policy.limitRange

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * Item that holds the min/max/default resource constraints for a particular type.
 */
@GeneratedDsl(withListGroup = true)
data class LimitRangeItem(
    /**
     * Type of resource that the limits apply to.
     */
    val type: String,
    /** default resource values enforced. */
    val default: Map<String, Quantity>? = null,
    /** default request values enforced. */
    val defaultRequest: Map<String, Quantity>? = null,
    /** maximum allowed usage. */
    val max: Map<String, Quantity>? = null,
    /** maxLimitRequestRatio specifies limit-to-request ratio. */
    val maxLimitRequestRatio: Map<String, Quantity>? = null,
    /** minimum allowed usage. */
    val min: Map<String, Quantity>? = null,
)
