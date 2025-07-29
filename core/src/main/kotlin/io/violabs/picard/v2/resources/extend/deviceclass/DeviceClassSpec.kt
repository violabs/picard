package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceClassSpec is used in a [DeviceClass] to define what can be allocated and how to configure it.
 */
@GeneratedDsl
data class DeviceClassSpec(
    /**
     * Config defines configuration parameters that apply to each device that is claimed via this class. 
     * Some classses may potentially be satisfied by multiple drivers, so each instance of a vendor 
     * configuration applies to exactly one driver.
     * 
     * They are passed to the driver, but are not considered while allocating the claim.
     */
    val config: List<DeviceClassConfiguration>? = null,
    /**
     * Each selector must be satisfied by a device which is claimed via this class.
     */
    val selectors: List<DeviceSelector>? = null
)