package io.violabs.picard.v2.resources.workload.resource.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceClaim

/**
 * ResourceClaimSpec defines what is being requested in a ResourceClaim and how to configure it.
 */
@GeneratedDsl
data class ResourceClaimSpec(
    /**
     * Devices defines how to request devices.
     */
    val devices: DeviceClaim? = null
)