package io.violabs.picard.v2.resources.extend.deviceclass.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.extend.deviceclass.config.OpaqueDeviceConfiguration

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