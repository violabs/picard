package io.violabs.picard.v2.resources.workload.pod.container.options

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SELinuxOptions are the labels to be applied to the container
 */
@GeneratedDsl
data class SeLinuxOptions(
    /**
     * Level is SELinux level label that applies to the container.
     */
    val level: String? = null,
    /**
     * Role is a SELinux role label that applies to the container.
     */
    val role: String? = null,
    /**
     * Type is a SELinux type label that applies to the container.
     */
    val type: String? = null,
    /**
     * User is a SELinux user label that applies to the container.
     */
    val user: String? = null
)