package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * DeviceCapacity describes a quantity associated with a device.
 */
@GeneratedDsl(withMapGroup = GeneratedDsl.MapGroupTypes.SINGLE)
data class DeviceCapacity(
    /**
     * Value defines how much of a certain device capacity is available.
     */
    val value: Quantity
)