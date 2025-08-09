package io.violabs.picard.v2.resources.workload.pod.container.state.user

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerUser represents user identity information
 */
@GeneratedDsl
data class ContainerUser(
    /**
     * Linux holds user identity information initially attached to the first
     * process of the containers in Linux.
     * Note that the actual running identity can be changed if the process has enough privilege to do so.
     */
    val linux: LinuxContainerUser? = null
)