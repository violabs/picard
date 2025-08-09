package io.violabs.picard.v2.resources.workload.pod.container.ephemeral

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvFromSource
import io.violabs.picard.v2.resources.workload.pod.container.env.EnvVar
import io.violabs.picard.v2.resources.workload.pod.container.security.ContainerSecurityContext
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeDevice
import io.violabs.picard.v2.resources.workload.pod.container.volume.VolumeMount

/**
 * An EphemeralContainer is a temporary container that you may add to an
 * existing Pod for user-initiated activities such as debugging.
 * Ephemeral containers have no resource or scheduling guarantees,
 * and they will not be restarted when they exit or when a Pod is removed or restarted.
 * The kubelet may evict a Pod if an ephemeral container causes the Pod to exceed its resource allocation.
 */
@GeneratedDsl(withListGroup = true)
data class EphemeralContainer(
    /**
     * Name of the ephemeral container specified as a DNS_LABEL.
     * This name must be unique among all containers, init containers and ephemeral containers.
     */
    val name: String,
    /**
     * Container image name. More info: https://kubernetes.io/docs/concepts/containers/images
     */
    val image: String? = null,
    /**
     * Image pull policy. One of Always, Never, IfNotPresent.
     * Defaults to Always if :latest tag is specified, or IfNotPresent otherwise.
     * Cannot be updated.
     * More info: https://kubernetes.io/docs/concepts/containers/images#updating-images
     */
    val imagePullPolicy: ImagePullPolicy? = null,
    /**
     * Entrypoint array.
     * Not executed within a shell.
     * The container image's ENTRYPOINT is used if this is not provided.
     * Variable references $(VAR_NAME) are expanded using the container's environment.
     * If a variable cannot be resolved, the reference in the input string will be unchanged.
     * Double $$ are reduced to a single $, which allows for escaping the $(VAR_NAME)
     * syntax: i.e. "$$(VAR_NAME)" will produce the string literal "$(VAR_NAME)".
     * Escaped references will never be expanded, regardless of whether the variable exists or not.
     * Cannot be updated.
     * More info: https://kubernetes.io/docs/tasks/inject-data-application/define-command-argument-container/#running-a-command-in-a-shell
     */
    val command: List<String>? = null,
    /**
     * Arguments to the entrypoint.
     * The container image's CMD is used if this is not provided.
     * Variable references $(VAR_NAME) are expanded using the container's environment.
     * If a variable cannot be resolved, the reference in the input string will be unchanged.
     * Double $$ are reduced to a single $, which allows for escaping the $(VAR_NAME)
     * syntax: i.e. "$$(VAR_NAME)" will produce the string literal "$(VAR_NAME)".
     * Escaped references will never be expanded, regardless of whether the variable exists or not.
     * Cannot be updated.
     * More info: https://kubernetes.io/docs/tasks/inject-data-application/define-command-argument-container/#running-a-command-in-a-shell
     */
    val args: List<String>? = null,
    /**
     * Container's working directory.
     * If not specified, the container runtime's default will be used,
     * which might be configured in the container image.
     * Cannot be updated.
     */
    val workingDir: String? = null,
    /**
     * List of environment variables to set in the container. Cannot be updated.
     */
    val env: List<EnvVar>? = null,
    /**
     * List of sources to populate environment variables in the container.
     * The keys defined within a source must be a C_IDENTIFIER.
     * All invalid keys will be reported as an event when the container is starting.
     * When a key exists in multiple sources, the value associated
     * with the last source will take precedence.
     * Values defined by an Env with a duplicate key will take precedence.
     * Cannot be updated.
     */
    val envFrom: List<EnvFromSource>? = null,
    /**
     * Pod volumes to mount into the container's filesystem.
     * Cannot be updated.
     */
    val volumeMounts: List<VolumeMount>? = null,
    /**
     * volumeDevices is the list of block devices to be used by the container.
     */
    val volumeDevices: List<VolumeDevice>? = null,
    /**
     * Optional: Path at which the file to which the container's termination
     * message will be written is mounted into the container's filesystem.
     * Message written is intended to be brief final status, such as an assertion failure message.
     * Will be truncated by the node if greater than 4096 bytes.
     * The total message length across all containers will be limited to 12kb.
     * Defaults to /dev/termination-log.
     * Cannot be updated.
     */
    val resizePolicy: List<ContainerResizePolicy>? = null,
    /**
     * Optional: Path at which the file to which the container's termination message will be written
     * is mounted into the container's filesystem. Message written is intended to be brief final status,
     * such as an assertion failure message. Will be truncated by the node if greater than 4096 bytes.
     * The total message length across all containers will be limited to 12kb.
     * Defaults to /dev/termination-log. Cannot be updated.
     */
    val terminationMessagePath: String? = null,
    /**
     * Indicate how the termination message should be populated.
     * File will use the contents of terminationMessagePath to populate the
     * container status message on both success and failure.
     * FallbackToLogsOnError will use the last chunk of container log output if
     * the termination message file is empty and the container exited with an error.
     * The log output is limited to 2048 bytes or 80 lines, whichever is smaller.
     * Defaults to File. Cannot be updated.
     */
    val terminationMessagePolicy: TerminationMessagePolicy? = null,
    /**
     * SecurityContext defines the security options the container should be run with.
     * If set, the fields of SecurityContext override the equivalent fields of PodSecurityContext.
     * More info: https://kubernetes.io/docs/tasks/configure-pod-container/security-context/
     */
    val securityContext: ContainerSecurityContext? = null,
    /**
     * Whether this container should allocate a buffer for stdin in the container runtime.
     * If this is not set, reads from stdin in the container will always result in EOF.
     * Default is false.
     */
    val stdin: Boolean? = null,
    /**
     * Whether the container runtime should close the stdin channel
     * after it has been opened by a single attach. When stdin is true the stdin
     * stream will remain open across multiple attach sessions.
     * If stdinOnce is set to true, stdin is opened on container start,
     * is empty until the first client attaches to stdin, and then remains open
     * and accepts data until the client disconnects, at which time stdin is closed and
     * remains closed until the container is restarted. If this flag is false, a container
     * processes that reads from stdin will never receive an EOF.
     * Default is false
     */
    val stdinOnce: Boolean? = null,
    /**
     * Whether this container should allocate a TTY for itself,
     * also requires 'stdin' to be true. Default is false.
     */
    val tty: Boolean? = null,
    /**
     * If set, the name of the container from PodSpec that this ephemeral container targets.
     * The ephemeral container will be run in the same process namespace as this container.
     */
    val targetContainerName: String? = null
) {
    enum class ImagePullPolicy {
        Always,
        Never,
        IfNotPresent
    }

    enum class TerminationMessagePolicy {
        File,
        FallbackToLogsOnError
    }
}