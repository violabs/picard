package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount

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
    val ports: List<ContainerPort>? = null,
    // Environment Variables
    override val env: List<EnvVar>? = null,
    override val envFrom: List<EnvFromSource>? = null,
    // Volumes
    override val volumeMounts: List<VolumeMount>? = null,
    override val volumeDevices: List<VolumeDevice>? = null,
    // Resources
    val resources: ContainerResourceRequirements? = null,
    // Lifecycle
    val lifecycle: Lifecycle? = null,
    override val terminationMessagePath: String? = null,
    override val terminationMessagePolicy: String? = null,
    val livenessProbe: Probe? = null,
    val readinessProbe: Probe? = null,
    val startupProbe: Probe? = null,
    override val restartPolicy: RestartPolicy? = null,
    // Security Context
    override val securityContext: ContainerSecurityContext? = null,
    // Debugging
    override val stdin: Boolean? = null,
    override val stdinOnce: Boolean? = null,
    override val tty: Boolean? = null,
) : BaseContainer {

    class Builder : BaseContainer.Builder<Container>() {
        private var ports: List<ContainerPort>? = null
        private var resources: ContainerResourceRequirements? = null
        private var lifecycle: Lifecycle? = null
        private var livenessProbe: Probe? = null
        private var readinessProbe: Probe? = null
        private var startupProbe: Probe? = null

        fun ports(scope: PortGroup.() -> Unit) {
            ports = PortGroup().apply(scope).ports()
        }

        fun resources(scope: ContainerResourceRequirements.Builder.() -> Unit) {
            resources = ContainerResourceRequirements.Builder().apply(scope).build()
        }

        fun lifecycle(scope: Lifecycle.Builder.() -> Unit) {
            lifecycle = Lifecycle.Builder().apply(scope).build()
        }

        fun livenessProbe(scope: Probe.Builder.() -> Unit) {
            livenessProbe = Probe.Builder().apply(scope).build()
        }

        fun readinessProbe(scope: Probe.Builder.() -> Unit) {
            readinessProbe = Probe.Builder().apply(scope).build()
        }

        fun startupProbe(scope: Probe.Builder.() -> Unit) {
            startupProbe = Probe.Builder().apply(scope).build()
        }

        override fun build(): Container {
            return Container(
                requireNotNull(name) { "name must not be null" },
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

        class PortGroup {
            private val content: MutableList<ContainerPort> = mutableListOf()

            fun ports(): List<ContainerPort> = content

            fun port(scope: ContainerPort.Builder.() -> Unit) {
                content.add(ContainerPort.Builder().apply(scope).build())
            }
        }
    }
}