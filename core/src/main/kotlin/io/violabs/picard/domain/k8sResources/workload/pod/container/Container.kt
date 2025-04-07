package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.*
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
import io.violabs.picard.validation.RangeLimit
import java.time.LocalDateTime

/**
 * @property name DNS_LABEL. must be unique.
 * @property image container image name.
 * @property imagePullPolicy defaults to [ImagePullPolicy.Always].
 * @property command entrypoint array. default container image ENTRYPOINT.
 * @property args arguments to the entrypoint. default container image CMD.
 * @property workingDir containers working directory. default is container runtime's default
 * @property ports container ports. unique values on keys containerPort and protocol
 */
data class Container(
    override val name: String,
    // Image
    override val image: String? = null,
    override val imagePullPolicy: ImagePullPolicy? = null,
    // Entrypoint
    override val command: List<String>? = null,
    override val args: List<String>? = null,
    override val workingDir: String? = null,
    // Ports
    val ports: List<Port>? = null,
    // Environment Variables
    override val env: List<BaseContainer.EnvVar>? = null,
    override val envFrom: List<BaseContainer.EnvFromSource>? = null,
    // Volumes
    override val volumeMounts: List<VolumeMount>? = null,
    override val volumeDevices: List<VolumeDevice>? = null,
    // Resources
    val resources: ResourceRequirements? = null,
    // Lifecycle
    val lifecycle: Lifecycle? = null,
    override val terminationMessagePath: String? = null,
    override val terminationMessagePolicy: String? = null,
    val livenessProbe: Probe? = null,
    val readinessProbe: Probe? = null,
    val startupProbe: Probe? = null,
    override val restartPolicy: RestartPolicy? = null,
    // Security Context
    override val securityContext: BaseContainer.SecurityContext? = null,
    // Debugging
    override val stdin: Boolean? = null,
    override val stdinOnce: Boolean? = null,
    override val tty: Boolean? = null,
) : BaseContainer {

    data class Port(
        @RangeLimit(1, 65535, required = true)
        val containerPort: Int,
        val hostIp: String? = null,
        @RangeLimit(1, 65535, required = true)
        val hostPort: Int? = null,
        val name: String? = null,
        val protocol: Protocol? = null
    ) {
        class Builder : DslBuilder<Port> {
            var containerPort: Int? = null
            var hostIp: String? = null
            var hostPort: Int? = null
            var name: String? = null
            var protocol: Protocol? = null

            override fun build(): Port {
                return Port(
                    requireNotNull(containerPort) { "Container port must not be null" },
                    hostIp,
                    hostPort,
                    name,
                    protocol
                )
            }
        }
    }

    data class ResourceClaim(
        val name: String,
        val request: String? = null
    ) {
        class Builder : DslBuilder<ResourceClaim> {
            var name: String? = null
            var request: String? = null
            override fun build(): ResourceClaim {
                return ResourceClaim(
                    requireNotNull(name) { "Name must not be null" },
                    request
                )
            }
        }
    }

    data class ResourceRequirements(
        val claims: List<ResourceClaim>? = null,
        val limits: Map<String, Quantity>? = null,
        val requests: Map<String, Quantity>? = null,
        val resizePolicy: List<ResizePolicy>? = null
    ) {
        class Builder : DslBuilder<ResourceRequirements> {
            var claims: List<ResourceClaim>? = null
            var limits: Map<String, Quantity>? = null
            var requests: Map<String, Quantity>? = null
            var resizePolicy: List<ResizePolicy>? = null
            override fun build(): ResourceRequirements {
                return ResourceRequirements(
                    claims,
                    limits,
                    requests,
                    resizePolicy
                )
            }
        }
    }

    data class ResizePolicy(
        val resourceName: String,
        val restartPolicy: String
    ) {
        class Builder : DslBuilder<ResizePolicy> {
            var resourceName: String? = null
            var restartPolicy: String? = null

            override fun build(): ResizePolicy {
                return ResizePolicy(
                    requireNotNull(resourceName) { "name must not be null" },
                    requireNotNull(restartPolicy) { "restartPolicy must not be null" }
                )
            }
        }
    }

    data class Lifecycle(
        val postStart: LifecycleHandler? = null,
        val preStop: LifecycleHandler? = null,
    ) {
        class Builder : DslBuilder<Lifecycle> {
            var postStart: LifecycleHandler? = null
            var preStop: LifecycleHandler? = null
            override fun build(): Lifecycle {
                return Lifecycle(
                    postStart,
                    preStop
                )
            }
        }
    }

    data class Status(
        val imageID: String,
        val image: String,
        val name: String,
        val ready: Boolean,
        val allocatedResources: Map<String, Quantity>? = null,
        val allocatedResourceStatus: List<Resource.Status>? = null,
        val containerID: String? = null,
        val lastState: State? = null,
        val resources: ResourceRequirements? = null,
        val restartCount: Int? = null,
        val started: Boolean? = null,
        val state: State? = null,
        val user: User? = null,
        val volumeMounts: VolumeMount.Status? = null
    ) {
        class Builder : DslBuilder<Status> {
            var imageID: String? = null
            var image: String? = null
            var name: String? = null
            var ready: Boolean? = null
            var allocatedResources: Map<String, Quantity>? = null
            var allocatedResourceStatus: List<Resource.Status>? = null
            var containerID: String? = null
            var lastState: State? = null
            var resources: ResourceRequirements? = null
            var restartCount: Int? = null
            var started: Boolean? = null
            var state: State? = null
            var user: User? = null
            var volumeMounts: VolumeMount.Status? = null

            override fun build(): Status {
                return Status(
                    requireNotNull(imageID) { "image ID must not be null" },
                    requireNotNull(image) { "image must not be null" },
                    requireNotNull(name) { "name must not be null" },
                    requireNotNull(ready) { "ready must not be null" },
                    allocatedResources,
                    allocatedResourceStatus,
                    containerID,
                    lastState,
                    resources,
                    restartCount,
                    started,
                    state,
                    user,
                    volumeMounts
                )
            }
        }
    }

    data class State(
        val running: Running? = null,
        val terminated: Terminated? = null,
        val waiting: Waiting? = null
    ) {
        data class Running(val startedAt: LocalDateTime? = null) {
            class Builder : DslBuilder<Running> {
                var startedAt: LocalDateTime? = null

                override fun build(): Running {
                    return Running(startedAt)
                }
            }
        }
        data class Terminated(
            val containerID: String? = null,
            val exitCode: Int? = null,
            val finishedAt: LocalDateTime? = null,
            val message: String? = null,
            val reason: String? = null,
            val signal: Int? = null,
            val startedAt: LocalDateTime? = null
        ) {
            class Builder : DslBuilder<Terminated> {
                var containerID: String? = null
                var exitCode: Int? = null
                var finishedAt: LocalDateTime? = null
                var message: String? = null
                var reason: String? = null
                var signal: Int? = null
                var startedAt: LocalDateTime? = null

                override fun build(): Terminated {
                    return Terminated(
                        containerID,
                        exitCode,
                        finishedAt,
                        message,
                        reason,
                        signal,
                        startedAt
                    )
                }
            }
        }

        data class Waiting(
            val message: String? = null,
            val reason: String? = null
        ) {
            class Builder : DslBuilder<Waiting> {
                var message: String? = null
                var reason: String? = null

                override fun build(): Waiting {
                    return Waiting(
                        message,
                        reason
                    )
                }
            }
        }

        class Builder : DslBuilder<State> {
            var running: Running? = null
            var terminated: Terminated? = null
            var waiting: Waiting? = null

            override fun build(): State {
                return State(
                    running,
                    terminated,
                    waiting
                )
            }
        }
    }

    data class User(
        val linux: LinuxContainerUser? = null
    ) {
        class Builder : DslBuilder<User> {
            var linux: LinuxContainerUser? = null

            override fun build(): User {
                return User(linux)
            }
        }
    }

    data class LinuxContainerUser(
        val gid: Long,
        val uid: Long,
        val supplementalGroups: List<Long>? = null
    ) {
        class Builder : DslBuilder<LinuxContainerUser> {
            var gid: Long? = null
            var uid: Long? = null
            var supplementalGroups: List<Long>? = null

            override fun build(): LinuxContainerUser {
                return LinuxContainerUser(
                    requireNotNull(gid) { "gid must not be null" },
                    requireNotNull(uid) { "uid must not be null" },
                    supplementalGroups
                )
            }
        }
    }

    data class Image(
        val names: List<String>? = null,
        val sizeBytes: Long? = null
    ) {
        class Builder : DslBuilder<Image> {
            var names: List<String>? = null
            var sizeBytes: Long? = null
            override fun build(): Image {
                return Image(
                    names = names,
                    sizeBytes = sizeBytes
                )
            }
        }
    }

    class Builder : DslBuilder<Container> {
        var name: String? = null
        var image: String? = null
        var imagePullPolicy: ImagePullPolicy? = null
        var command: List<String>? = null
        var args: List<String>? = null
        var workingDir: String? = null
        var ports: List<Port>? = null
        var env: List<BaseContainer.EnvVar>? = null
        var envFrom: List<BaseContainer.EnvFromSource>? = null
        val volumeMounts: List<VolumeMount>? = null
        val volumeDevices: List<VolumeDevice>? = null
        val resources: ResourceRequirements? = null
        val lifecycle: Lifecycle? = null
        val terminationMessagePath: String? = null
        val terminationMessagePolicy: String? = null
        val livenessProbe: Probe? = null
        val readinessProbe: Probe? = null
        val startupProbe: Probe? = null
        val restartPolicy: RestartPolicy? = null
        val securityContext: BaseContainer.SecurityContext? = null
        val stdin: Boolean? = null
        val stdinOnce: Boolean? = null
        val tty: Boolean? = null

        override fun build(): Container {
            return Container(
                requireNotNull(name) { "Container name must not be null" },
                image,
                imagePullPolicy,
                command,
                args,
                workingDir,
                ports,
                env,
                envFrom,
                volumeMounts,
                volumeDevices,
                resources,
                lifecycle,
                terminationMessagePath,
                terminationMessagePolicy,
                livenessProbe,
                readinessProbe,
                startupProbe,
                restartPolicy,
                securityContext,
                stdin,
                stdinOnce,
                tty
            )
        }
    }
}