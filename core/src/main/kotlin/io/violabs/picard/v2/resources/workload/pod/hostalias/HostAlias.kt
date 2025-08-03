package io.violabs.picard.v2.resources.workload.pod.hostalias

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * HostAlias holds the mapping between IP and hostnames that will be injected as an entry in the pod's hosts file.
 */
@GeneratedDsl(withListGroup = true)
data class HostAlias(
    /**
     * IP address of the host file entry.
     */
    val ip: String,
    /**
     * Hostnames for the above IP address.
     *
     * Atomic: will be replaced during a merge
     */
    val hostnames: List<String>? = null
)