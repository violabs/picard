package io.violabs.picard.v2.resources.workload.pod.container.state

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * ContainerStateTerminated is a terminated state of a container.
 */
@GeneratedDsl
data class ContainerStateTerminated(
    /**
     * Container's ID in the format '<type>://<container_id>'
     */
    @JsonProperty("containerID")
    val containerId: String? = null,
    /**
     * Exit status from the last termination of the container
     */
    val exitCode: Int,
    /**
     * Time at which the container last terminated
     */
    val finishedAt: LocalDateTime? = null,
    /**
     * Message regarding the last termination of the container
     */
    val message: String? = null,
    /**
     * (brief) reason from the last termination of the container
     */
    val reason: String? = null,
    /**
     * Signal from the last termination of the container
     */
    val signal: Int? = null,
    /**
     * Time at which previous execution of the container started
     */
    val startedAt: LocalDateTime? = null
)