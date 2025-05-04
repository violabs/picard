package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.ImagePullPolicy
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount

interface BaseContainer {
    val name: String
    val image: String?
    val imagePullPolicy: ImagePullPolicy?

    // Entrypoint
    val command: List<String>?
    val args: List<String>?
    val workingDir: String?

    // Environment Variables
    val env: List<EnvVar>?
    val envFrom: List<EnvFromSource>?

    // Volumes
    val volumeMounts: List<VolumeMount>?
    val volumeDevices: List<VolumeDevice>?

    // Lifecycle
    val terminationMessagePath: String?
    val terminationMessagePolicy: String?
    val restartPolicy: RestartPolicy?

    // Security Context
    val securityContext: ContainerSecurityContext?

    // Debugging
    val stdin: Boolean?
    val stdinOnce: Boolean?
    val tty: Boolean?

    abstract class Builder<T : BaseContainer> : DSLBuilder<T> {
        var name: String? = null
        var image: String? = null
        var imagePullPolicy: ImagePullPolicy? = null
        protected var command: List<String>? = null
        protected var args: List<String>? = null
        var workingDir: String? = null
        protected var env: List<EnvVar>? = null
        protected var envFrom: List<EnvFromSource>? = null
        protected var volumeMounts: List<VolumeMount>? = null
        protected var volumeDevices: List<VolumeDevice>? = null
        var terminationMessagePath: String? = null
        var terminationMessagePolicy: String? = null
        var restartPolicy: RestartPolicy? = null
        var securityContext: ContainerSecurityContext? = null
        var stdin: Boolean? = null
        var stdinOnce: Boolean? = null
        var tty: Boolean? = null


        fun command(vararg commands: String) {
            command = listOf(*commands)
        }

        fun args(vararg args: String) {
            this.args = listOf(*args)
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

        fun securityContext(scope: ContainerSecurityContext.Builder.() -> Unit) {
            securityContext = ContainerSecurityContext.Builder().apply(scope).build()
        }

        fun stdin() {
            stdin = true
        }

        fun stdinOnce() {
            stdinOnce = true
        }

        fun tty() {
            tty = true
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