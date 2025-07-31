package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceCIDRStatus describes the current state of the ServiceCIDR.
 */
@GeneratedDsl
data class ServiceCidrStatus(
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * conditions holds an array of metav1.Condition that describe the state of the ServiceCIDR.
     * Current service state
     */
    val conditions: List<Condition>? = null
)