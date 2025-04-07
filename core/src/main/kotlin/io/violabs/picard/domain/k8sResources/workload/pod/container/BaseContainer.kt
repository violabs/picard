package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.ObjectFieldSelector
import io.violabs.picard.domain.ResourceFieldSelector
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.BaseResourceMetricSource
import io.violabs.picard.domain.BaseResourceMetricStatus
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
    val imagePullPolicy: String?
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


    data class EnvVar(
        val name: String,
        val value: String? = null,
        val valueFrom: Source? = null
    ) {
        data class Source(
            val configMapKeyRef: ConfigMap.KeySelector? = null,
            val fieldRef: ObjectFieldSelector? = null,
            val resourceFieldRef: ResourceFieldSelector? = null,
            val secretKeyRef: Secret.KeySelector? = null
        )
    }

    data class EnvFromSource(
        val configMapRef: ConfigMap.EnvSource? = null,
        val prefix: String? = null,
        val secretRef: Secret.EnvSource? = null
    )

    data class SecurityContext(
        val allowPrivilegeEscalation: Boolean? = null,
        val appArmorProfile: AppArmorProfile? = null,
        val capabilities: Capabilities? = null,
        val procMount: String? = null,
        val privileged: Boolean? = null,
        val readOnlyRootFilesystem: Boolean? = null,
        val runAsUser: Long? = null,
        val runAsNonRoot: Boolean? = null,
        val runAsGroup: Long? = null,
        val seLinuxOptions: SELinuxOptions? = null,
        val seccompProfile: SeccompProfile? = null,
        val windowsOptions: WindowsSecurityContextOptions? = null
    ) {
        data class Capabilities(
            val add: List<String>? = null,
            val drop: List<String>? = null
        )
    }

    data class ResourceMetricSource(
        val container: String,
        val name: String,
        val target: Metric.Target? = null
    ) : BaseResourceMetricSource

    data class ResourceMetricStatus(
        val container: String,
        val current: Metric.ValueStatus,
        val name: String
    ) : BaseResourceMetricStatus
}