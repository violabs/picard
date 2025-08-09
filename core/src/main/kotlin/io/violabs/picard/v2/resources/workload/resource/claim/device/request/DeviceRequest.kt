package io.violabs.picard.v2.resources.workload.resource.claim.device.request

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceRequest is a request for devices required for a claim. This is typically a request for 
 * a single resource like a device, but can also ask for several identical devices. 
 * With FirstAvailable it is also possible to provide a prioritized list of requests.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceRequest(
    /**
     * Name can be used to reference this request in a pod.spec.containers[].resources.claims entry 
     * and in a constraint of the claim.
     * 
     * References using the name in the DeviceRequest will uniquely identify a request when the Exactly 
     * field is set. When the FirstAvailable field is set, a reference to the name of the DeviceRequest 
     * will match whatever subrequest is chosen by the scheduler.
     * 
     * Must be a DNS label.
     */
    val name: String,
    /**
     * Exactly specifies the details for a single request that must be met exactly for the request to be satisfied.
     * 
     * One of Exactly or FirstAvailable must be set.
     */
    val exactly: ExactDeviceRequest? = null,
    /**
     * FirstAvailable contains subrequests, of which exactly one will be selected by the scheduler. 
     * It tries to satisfy them in the order in which they are listed here. So if there are two entries 
     * in the list, the scheduler will only check the second one if it determines that the first one can not be used.
     * 
     * DRA does not yet implement scoring, so the scheduler will select the first set of devices that 
     * satisfies all the requests in the claim. And if the requirements can be satisfied on more than one node, 
     * other scheduling features will determine which node is chosen. This means that the set of devices allocated 
     * to a claim might not be the optimal set available to the cluster. Scoring will be implemented later.
     * 
     * Atomic: will be replaced during a merge
     */
    val firstAvailable: List<DeviceSubRequest>? = null
)