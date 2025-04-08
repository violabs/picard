package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.Probe
import io.violabs.picard.domain.k8sResources.workload.pod.Resource
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
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
    override val securityContext: SecurityContext? = null,
    // Debugging
    override val stdin: Boolean? = null,
    override val stdinOnce: Boolean? = null,
    override val tty: Boolean? = null,
) : BaseContainer {

    class Builder : DslBuilder<Container> {
        var name: String? = null
        var image: String? = null
        var imagePullPolicy: ImagePullPolicy? = null
        var command: List<String>? = null
        var args: List<String>? = null
        var workingDir: String? = null
        private var ports: List<ContainerPort>? = null
        private var env: List<EnvVar>? = null
        private var envFrom: List<EnvFromSource>? = null
        private var volumeMounts: List<VolumeMount>? = null
        private var volumeDevices: List<VolumeDevice>? = null
        private var resources: ContainerResourceRequirements? = null
        private var lifecycle: Lifecycle? = null
        var terminationMessagePath: String? = null
        var terminationMessagePolicy: String? = null
        private var livenessProbe: Probe? = null
        private var readinessProbe: Probe? = null
        private var startupProbe: Probe? = null
        var restartPolicy: RestartPolicy? = null
        private var securityContext: SecurityContext? = null
        var stdin: Boolean? = null
        var stdinOnce: Boolean? = null
        var tty: Boolean? = null

        fun command(vararg commands: String) {
            command = listOf(*commands)
        }

        fun args(vararg args: String) {
            this.args = listOf(*args)
        }

        fun ports(scope: PortGroup.() -> Unit) {
            ports = PortGroup().apply(scope).ports()
        }

        fun env(scope: EnvVarGroup.() -> Unit) {
            env = EnvVarGroup().apply(scope).envVars()
        }

        fun envFrom(scope: EnvFromSourceGroup.() -> Unit) {
            envFrom = EnvFromSourceGroup().apply(scope).envFromSources()
        }

        fun volumeMounts(scope: VolumeMountGroup.() -> Unit) {
            volumeMounts = VolumeMountGroup().apply(scope).volumeMounts()
        }

        fun volumeDevices(scope: VolumeDeviceGroup.() -> Unit) {
            volumeDevices = VolumeDeviceGroup().apply(scope).volumeDevices()
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

        fun securityContext(scope: SecurityContext.Builder.() -> Unit) {
            securityContext = SecurityContext.Builder().apply(scope).build()
        }

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

        class PortGroup {
            private val content: MutableList<ContainerPort> = mutableListOf()

            fun ports(): List<ContainerPort> = content

            fun port(scope: ContainerPort.Builder.() -> Unit) {
                content.add(ContainerPort.Builder().apply(scope).build())
            }
        }

        class EnvVarGroup {
            private val content: MutableList<EnvVar> = mutableListOf()

            fun envVars(): List<EnvVar> = content

            fun add(scope: EnvVar.Builder.() -> Unit) {
                content.add(EnvVar.Builder().apply(scope).build())
            }
        }

        class EnvFromSourceGroup {
            private val content: MutableList<EnvFromSource> = mutableListOf()

            fun envFromSources(): List<EnvFromSource> = content

            fun add(scope: EnvFromSource.Builder.() -> Unit) {
                content.add(EnvFromSource.Builder().apply(scope).build())
            }
        }

        class VolumeMountGroup {
            private val content: MutableList<VolumeMount> = mutableListOf()

            fun volumeMounts(): List<VolumeMount> = content

            fun volumeMount(scope: VolumeMount.Builder.() -> Unit) {
                content.add(VolumeMount.Builder().apply(scope).build())
            }
        }

        class VolumeDeviceGroup {
            private val content: MutableList<VolumeDevice> = mutableListOf()

            fun volumeDevices(): List<VolumeDevice> = content

            fun volumeDevice(scope: VolumeDevice.Builder.() -> Unit) {
                content.add(VolumeDevice.Builder().apply(scope).build())
            }
        }
    }
}