package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.*
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
import java.time.LocalDateTime

data class Container(
    override val name: String,
    // Image
    override val image: String? = null,
    override val imagePullPolicy: String? = null,
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
        val containerPort: Int,
        val hostIp: String? = null,
        val hostPort: Int? = null,
        val name: String? = null,
        val protocol: String? = null
    )

    data class ResourceClaim(
        val name: String,
        val request: String? = null
    )

    data class ResourceRequirements(
        val claims: List<ResourceClaim>? = null,
        val limits: Map<String, Quantity>? = null,
        val requests: Map<String, Quantity>? = null,
        val resizePolicy: List<ResizePolicy>? = null
    )

    data class ResizePolicy(
        val resourceName: String,
        val restartPolicy: String
    )

    data class Lifecycle(
        val postStart: LifecycleHandler? = null,
        val preStop: LifecycleHandler? = null,
    )

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
    )

    data class State(
        val running: Running? = null,
        val terminated: Terminated? = null,
    ) {
        data class Running(val startedAt: LocalDateTime? = null)
        data class Terminated(
            val containerID: String? = null,
            val exitCode: Int? = null,
            val finishedAt: LocalDateTime? = null,
            val message: String? = null,
            val reason: String? = null,
            val signal: Int? = null,
            val startedAt: LocalDateTime? = null
        )

        data class Waiting(
            val message: String? = null,
            val reason: String? = null
        )
    }

    data class User(
        val linux: LinuxContainerUser? = null
    )

    data class LinuxContainerUser(
        val gid: Long,
        val uid: Long,
        val supplementalGroups: List<Long>? = null
    )

    data class Image(
        val names: List<String>? = null,
        val sizeBytes: Long? = null
    )
}