package io.violabs.picard.v2.resources.workload.resource.claim.device

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.claim.device.config.DeviceClaimConfiguration
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.DeviceRequest

/**
 * DeviceClaim defines how to request devices with a ResourceClaim.
 */
@GeneratedDsl
data class DeviceClaim(
    /**
     * This field holds configuration for multiple potential drivers which could satisfy requests in this claim. 
     * It is ignored while allocating the claim.
     * 
     * Atomic: will be replaced during a merge
     */
    val config: List<DeviceClaimConfiguration>? = null,
    /**
     * These constraints must be satisfied by the set of devices that get allocated for the claim.
     * 
     * Atomic: will be replaced during a merge
     */
    val constraints: List<DeviceConstraint>? = null,
    /**
     * Requests represent individual requests for distinct devices which must all be satisfied. 
     * If empty, nothing needs to be allocated.
     * 
     * Atomic: will be replaced during a merge
     */
    val requests: List<DeviceRequest>? = null
)