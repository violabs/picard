package io.violabs.picard.v2.resources.workload.pod.container.state

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerStateWaiting is a waiting state of a container.
 */
@GeneratedDsl
data class ContainerStateWaiting(
    /**
     * Message regarding why the container is not yet running.
     */
    val message: String? = null,
    /**
     * (brief) reason the container is not yet running.
     */
    val reason: String? = null
)