package io.violabs.picard.v2.resources.workload.resource.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.NodeSelector
import io.violabs.picard.v2.resources.workload.resource.claim.device.result.DeviceAllocationResult

/**
 * AllocationResult contains attributes of an allocated resource.
 */
@GeneratedDsl(withListGroup = true)
data class AllocationResult(
    /**
     * Devices is the result of allocating devices.
     */
    val devices: DeviceAllocationResult? = null,
    /**
     * NodeSelector defines where the allocated resources are available. If unset, they are available everywhere.
     */
    val nodeSelector: NodeSelector? = null
)