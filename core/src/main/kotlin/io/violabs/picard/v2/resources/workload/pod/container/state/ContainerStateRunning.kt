package io.violabs.picard.v2.resources.workload.pod.container.state

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * ContainerStateRunning is a running state of a container.
 */
@GeneratedDsl
data class ContainerStateRunning(
    /**
     * Time at which the container was last (re-)started
     */
    val startedAt: LocalDateTime? = null
)