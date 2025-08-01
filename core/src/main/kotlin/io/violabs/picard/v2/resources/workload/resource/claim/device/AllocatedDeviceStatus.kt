package io.violabs.picard.v2.resources.workload.resource.claim.device

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * AllocatedDeviceStatus contains the status of an allocated device, if the driver chooses to report it.
 * This may include driver-specific information.
 */
@GeneratedDsl(withListGroup = true)
data class AllocatedDeviceStatus(
    /**
     * Device references one device instance via its name in the driver's resource pool. It must be a DNS label.
     */
    val device: String,
    /**
     * Driver specifies the name of the DRA driver whose kubelet plugin should be invoked to process
     * the allocation once the claim is needed on a node.
     *
     * Must be a DNS subdomain and should end with a DNS domain owned by the vendor of the driver.
     */
    val driver: String,
    /**
     * This name together with the driver name and the device name field identify which device was allocated
     * (<driver name>/<pool name>/<device name>).
     *
     * Must not be longer than 253 characters and may contain one or more DNS sub-domains separated by slashes.
     */
    val pool: String,
    /**
     * Conditions contains the latest observation of the device's state. If the device has been configured
     * according to the class and claim config references, the Ready condition should be True.
     *
     * Must not contain more than 8 entries.
     *
     * Map: unique values on key type will be kept during a merge
     */
    val conditions: List<Condition>? = null,
    /**
     * Data contains arbitrary driver-specific data.
     *
     * The length of the raw data must be smaller or equal to 10 Ki.
     *
     * RawExtension is used to hold extensions in external versions.
     */
    val data: Any? = null,
    /**
     * NetworkData contains network-related information specific to the device.
     */
    val networkData: NetworkDeviceData? = null
)