package io.violabs.picard.v2.resources.workload.resource.claim.device.result

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.claim.device.DeviceToleration

/**
 * DeviceRequestAllocationResult contains the allocation result for one request.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceRequestAllocationResult(
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
     * Request is the name of the request in the claim which caused this device to be allocated. 
     * If it references a subrequest in the firstAvailable list on a DeviceRequest, this field must 
     * include both the name of the main request and the subrequest using the format <main request>/<subrequest>.
     * 
     * Multiple devices may have been allocated per request.
     */
    val request: String,
    /**
     * AdminAccess indicates that this device was allocated for administrative access. 
     * See the corresponding request field for a definition of mode.
     * 
     * This is an alpha field and requires enabling the DRAAdminAccess feature gate. 
     * Admin access is disabled if this field is unset or set to false, otherwise it is enabled.
     */
    val adminAccess: Boolean? = null,
    /**
     * A copy of all tolerations specified in the request at the time when the device got allocated.
     * 
     * The maximum number of tolerations is 16.
     * 
     * This is an alpha field and requires enabling the DRADeviceTaints feature gate.
     * 
     * Atomic: will be replaced during a merge
     */
    val tolerations: List<DeviceToleration>? = null
)