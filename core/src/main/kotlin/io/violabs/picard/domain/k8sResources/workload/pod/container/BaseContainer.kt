package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.workload.Metric
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeDevice
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMount
import io.violabs.picard.domain.k8sResources.workload.pod.security.AppArmorProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.SELinuxOptions
import io.violabs.picard.domain.k8sResources.workload.pod.security.SeccompProfile
import io.violabs.picard.domain.k8sResources.workload.pod.security.WindowsSecurityContextOptions

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
    val securityContext: SecurityContext?
    // Debugging
    val stdin: Boolean?
    val stdinOnce: Boolean?
    val tty: Boolean?
}