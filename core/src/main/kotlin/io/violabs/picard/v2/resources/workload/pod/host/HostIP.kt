package io.violabs.picard.v2.resources.workload.pod.host

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * HostIP represents a single IP address allocated to the host.
 */
@GeneratedDsl(withListGroup = true)
data class HostIP(
    /**
     * IP is the IP address assigned to the host
     */
    val ip: String
)