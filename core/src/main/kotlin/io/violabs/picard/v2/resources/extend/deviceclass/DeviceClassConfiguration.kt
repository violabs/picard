package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceClassConfiguration is used in DeviceClass.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceClassConfiguration(
    /**
     * Opaque provides driver-specific configuration parameters.
     */
    val opaque: OpaqueDeviceConfiguration? = null
)