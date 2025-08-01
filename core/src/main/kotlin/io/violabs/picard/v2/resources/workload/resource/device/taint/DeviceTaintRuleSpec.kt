package io.violabs.picard.v2.resources.workload.resource.device.taint

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.device.selector.DeviceTaintSelector

/**
 * DeviceTaintRuleSpec specifies the selector and one taint.
 */
@GeneratedDsl
data class DeviceTaintRuleSpec(
    /**
     * The taint that gets applied to matching devices.
     * 
     * The device this taint is attached to has the "effect" on any claim which does not 
     * tolerate the taint and, through the claim, to pods using the claim.
     */
    val taint: DeviceTaint,
    /**
     * DeviceSelector defines which device(s) the taint is applied to. All selector criteria 
     * must be satisfied for a device to match. The empty selector matches all devices. 
     * Without a selector, no devices are matched.
     */
    val deviceSelector: DeviceTaintSelector? = null
)