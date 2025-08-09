package io.violabs.picard.v2.resources.workload.resource.claim.device.result

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.DeviceAllocationConfiguration

/**
 * DeviceAllocationResult is the result of allocating devices.
 */
@GeneratedDsl
data class DeviceAllocationResult(
    /**
     * This field is a combination of all the claim and class configuration parameters. 
     * Drivers can distinguish between those based on a flag.
     * 
     * This includes configuration parameters for drivers which have no allocated devices 
     * in the result because it is up to the drivers which configuration parameters they support. 
     * They can silently ignore unknown configuration parameters.
     * 
     * Atomic: will be replaced during a merge
     */
    val config: List<DeviceAllocationConfiguration>? = null,
    /**
     * Results lists all allocated devices.
     * 
     * Atomic: will be replaced during a merge
     */
    val results: List<DeviceRequestAllocationResult>? = null
)