package io.violabs.picard.v2.resources.workload.resource.claim.device

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NetworkDeviceData provides network-related details for the allocated device. 
 * This information may be filled by drivers or other components to configure 
 * or identify the device within a network context.
 */
@GeneratedDsl
data class NetworkDeviceData(
    /**
     * HardwareAddress represents the hardware address (e.g. MAC Address) of the device's network interface.
     * 
     * Must not be longer than 128 characters.
     */
    val hardwareAddress: String? = null,
    /**
     * InterfaceName specifies the name of the network interface associated with the allocated device. 
     * This might be the name of a physical or virtual network interface being configured in the pod.
     * 
     * Must not be longer than 256 characters.
     */
    val interfaceName: String? = null,
    /**
     * IPs lists the network addresses assigned to the device's network interface. 
     * This can include both IPv4 and IPv6 addresses. The IPs are in the CIDR notation, 
     * which includes both the address and the associated subnet mask. 
     * e.g.: "192.0.2.5/24" for IPv4 and "2001:db8::5/64" for IPv6.
     * 
     * Atomic: will be replaced during a merge
     */
    val ips: List<String>? = null
)