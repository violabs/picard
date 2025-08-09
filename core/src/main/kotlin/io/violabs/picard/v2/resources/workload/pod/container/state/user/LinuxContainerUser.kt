package io.violabs.picard.v2.resources.workload.pod.container.state.user

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * LinuxContainerUser represents user identity information in Linux containers
 */
@GeneratedDsl
data class LinuxContainerUser(
    /**
     * GID is the primary gid initially attached to the first process in the container
     */
    val gid: Long? = null,
    /**
     * UID is the primary uid initially attached to the first process in the container
     */
    val uid: Long? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * SupplementalGroups are the supplemental groups initially attached to the first process in the container
     */
    val supplementalGroups: List<Long>? = null
)