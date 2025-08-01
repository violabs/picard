package io.violabs.picard.v2.resources.workload.resource.claim.device.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceAllocationConfiguration gets embedded in an AllocationResult.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceAllocationConfiguration(
    /**
     * Source records whether the configuration comes from a class and thus is not something 
     * that a normal user would have been able to set or from a claim.
     */
    val source: String,
    /**
     * Opaque provides driver-specific configuration parameters.
     */
    val opaque: OpaqueDeviceConfiguration? = null,
    /**
     * Requests lists the names of requests where the configuration applies. 
     * If empty, its applies to all requests.
     * 
     * References to subrequests must include the name of the main request and may include 
     * the subrequest using the format <main request>[/<subrequest>]. 
     * If just the main request is given, the configuration applies to all subrequests.
     * 
     * Atomic: will be replaced during a merge
     */
    val requests: List<String>? = null
)