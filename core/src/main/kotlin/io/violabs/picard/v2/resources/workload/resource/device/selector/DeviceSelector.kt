package io.violabs.picard.v2.resources.workload.resource.device.selector

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceSelector must have exactly one field set.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceSelector(
    /**
     * CEL contains a CEL expression for selecting a device.
     */
    val cel: CelDeviceSelector? = null
)