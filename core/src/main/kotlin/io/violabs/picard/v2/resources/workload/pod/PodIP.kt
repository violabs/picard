package io.violabs.picard.v2.resources.workload.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PodIP represents a single IP address allocated to the pod.
 */
@GeneratedDsl(withListGroup = true)
data class PodIP(
    /**
     * IP is the IP address assigned to the pod
     */
    val ip: String
)