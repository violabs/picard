package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.pod.container.state.ContainerState

/**
 * ContainerStatus contains details for the current status of this container.
 */
@GeneratedDsl(withListGroup = true)
data class ContainerStatus(
    /**
     * Name is a DNS_LABEL representing the unique name of the container.
     * Each container in a pod must have a unique name across all container types.
     * Cannot be updated.
     */
    val name: String,
    /**
     * Ready specifies whether the container is currently passing its readiness check.
     * The value will change as readiness probes keep executing.
     * If no readiness probes are specified, this field defaults to true once the container is fully started.
     */
    val ready: Boolean = false,
    /**
     * RestartCount holds the number of times the container has been restarted.
     * Kubelet makes an effort to always increment the value, but there are cases
     * when the state may be lost due to node restarts and then the value may be
     * reset to 0. The value is never negative.
     */
    val restartCount: Int = 0,
    /**
     * Image is the name of container image that the container is running.
     * The container image may not match the image used in the PodSpec,
     * as it may have been resolved by the runtime.
     * More info: https://kubernetes.io/docs/concepts/containers/images.
     */
    val image: String? = null,
    /**
     * ImageID is the image ID of the container's image.
     * The image ID may not match the image ID of the image used in the PodSpec,
     * as it may have been resolved by the runtime.
     */
    val imageID: String? = null,
    /**
     * ContainerID is the ID of the container in the format '<type>://<container_id>'.
     * Where type is a container runtime identifier, returned from Version call of CRI API
     * (for example "containerd").
     */
    val containerID: String? = null,
    /**
     * Started indicates whether the container has finished its postStart hook.
     * If the PostStart hook is specified, this is set to true after it finishes.
     * If the PostStart hook is not specified, this is set to true after the container is fully started.
     * If the container fails to start, this will be set to false.
     * Reset to false when the container is restarted, or if the container has not yet been started.
     */
    val started: Boolean? = null,
    /**
     * State holds details about the container's current condition.
     */
    val state: ContainerState? = null,
    /**
     * LastState holds details about the container's last termination condition.
     */
    val lastState: ContainerState? = null
)