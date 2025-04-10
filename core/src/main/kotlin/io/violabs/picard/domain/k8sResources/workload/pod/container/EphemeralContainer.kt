package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount

data class EphemeralContainer(
    override val name: String,
    val targetContainerName: String? = null,
    // Image
    override val image: String? = null,
    override val imagePullPolicy: ImagePullPolicy? = null,
    // Entrypoint
    override val command: List<String>? = null,
    override val args: List<String>? = null,
    override val workingDir: String? = null,
    // Environment Variables
    override val env: List<EnvVar>? = null,
    override val envFrom: List<EnvFromSource>? = null,
    // Volumes
    override val volumeMounts: List<VolumeMount>? = null,
    override val volumeDevices: List<VolumeDevice>? = null,
    // Lifecycle
    override val terminationMessagePath: String? = null,
    override val terminationMessagePolicy: String? = null,
    override val restartPolicy: RestartPolicy? = null,
    // Security Context
    override val securityContext: ContainerSecurityContext? = null,
    // Debugging
    override val stdin: Boolean? = null,
    override val stdinOnce: Boolean? = null,
    override val tty: Boolean? = null,
) : BaseContainer {
    class Builder : BaseContainer.Builder<EphemeralContainer>() {
        var targetContainerName: String? = null

        override fun build(): EphemeralContainer {
            return EphemeralContainer(
                requireNotNull(name) { "name must not be null" },
                targetContainerName,
                image,
                imagePullPolicy,
                command,
                args,
                workingDir,
                env,
                envFrom,
                volumeMounts,
                volumeDevices,
                terminationMessagePath,
                terminationMessagePolicy,
                restartPolicy,
                securityContext,
                stdin,
                stdinOnce,
                tty
            )
        }
    }
}