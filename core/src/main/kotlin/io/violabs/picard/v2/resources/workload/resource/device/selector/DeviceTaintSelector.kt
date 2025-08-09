package io.violabs.picard.v2.resources.workload.resource.device.selector

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceTaintSelector defines which device(s) a DeviceTaintRule applies to.
 * The empty selector matches all devices. Without a selector, no devices are matched.
 */
@GeneratedDsl
data class DeviceTaintSelector(
    /**
     * If device is set, only devices with that name are selected.
     * This field corresponds to slice.spec.devices[].name.
     *
     * Setting also driver and pool may be required to avoid ambiguity, but is not required.
     */
    val device: String? = null,
    /**
     * If DeviceClassName is set, the selectors defined there must be satisfied by a device to be selected.
     * This field corresponds to class.metadata.name.
     */
    val deviceClassName: String? = null,
    /**
     * If driver is set, only devices from that driver are selected.
     * This fields corresponds to slice.spec.driver.
     */
    val driver: String? = null,
    /**
     * If pool is set, only devices in that pool are selected.
     *
     * Also setting the driver name may be useful to avoid ambiguity when different drivers use
     * the same pool name, but this is not required because selecting pools from different drivers
     * may also be useful, for example when drivers with node-local devices use the node name as
     * their pool name.
     */
    val pool: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * Selectors contains the same selection criteria as a ResourceClaim. Currently, CEL expressions
     * are supported. All of these selectors must be satisfied.
     */
    val selectors: List<DeviceSelector>? = null
)