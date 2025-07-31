package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceCIDRSpec define the CIDRs the user wants to use for allocating ClusterIPs for Services.
 */
@GeneratedDsl
data class ServiceCidrSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * CIDRs defines the IP blocks in CIDR notation (e.g. "192.168.0.0/24" or "2001:db8::/64")
     * from which to assign service cluster IPs. Max of two CIDRs is allowed, one of each IP family.
     * This field is immutable.
     */
    val cidrs: List<String>? = null
)