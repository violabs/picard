package io.violabs.picard.v2.resources.workload.pod.container.state

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerState holds a possible state of container.
 * Only one of its members may be specified.
 * If none of them is specified, the default one is ContainerStateWaiting.
 */
@GeneratedDsl
data class ContainerState(
    /**
     * Details about a running container
     */
    val running: ContainerStateRunning? = null,
    /**
     * Details about a terminated container
     */
    val terminated: ContainerStateTerminated? = null,
    /**
     * Details about a waiting container
     */
    val waiting: ContainerStateWaiting? = null,
)