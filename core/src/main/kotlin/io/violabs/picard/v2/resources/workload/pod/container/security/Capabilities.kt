package io.violabs.picard.v2.resources.workload.pod.container.security

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Adds and removes POSIX capabilities from running containers.
 */
@GeneratedDsl
data class Capabilities(
    /**
     * Added capabilities
     */
    val add: List<String>? = null,
    /**
     * Removed capabilities
     */
    val drop: List<String>? = null
)