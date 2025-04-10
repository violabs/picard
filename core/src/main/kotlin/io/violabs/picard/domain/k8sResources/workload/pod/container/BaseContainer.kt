package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.*
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
}