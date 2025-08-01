package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * Counter describes a quantity associated with a device.
 */
@GeneratedDsl(withMapGroup = GeneratedDsl.MapGroupTypes.SINGLE)
data class Counter(
    /**
     * Value defines how much of a certain device counter is available.
     */
    val value: Quantity
)