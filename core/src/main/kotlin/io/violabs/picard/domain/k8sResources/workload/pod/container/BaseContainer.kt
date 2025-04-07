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
        ) {
            class Builder : DslBuilder<Source> {
                private var configMapKeyRef: ConfigMap.KeySelector? = null
                private var fieldRef: ObjectFieldSelector? = null
                private var resourceFieldRef: ResourceFieldSelector? = null
                private var secretKeyRef: Secret.KeySelector? = null

                override fun build(): Source {
                    return Source(
                        configMapKeyRef = configMapKeyRef,
                        fieldRef = fieldRef,
                        resourceFieldRef = resourceFieldRef,
                        secretKeyRef = secretKeyRef
                    )
                }
            }
        }

        class Builder : DslBuilder<EnvVar> {
            private var name: String? = null
            private var value: String? = null
            private var valueFrom: Source? = null

            override fun build(): EnvVar {
                return EnvVar(
                    name = name ?: error("name is required"),
                    value = value,
                    valueFrom = valueFrom
                )
            }
        }
    }

    data class EnvFromSource(
        val configMapRef: ConfigMap.EnvSource? = null,
        val prefix: String? = null,
        val secretRef: Secret.EnvSource? = null
    ) {
        class Builder : DslBuilder<EnvFromSource> {
            private var configMapRef: ConfigMap.EnvSource? = null
            private var prefix: String? = null
            private var secretRef: Secret.EnvSource? = null

            override fun build(): EnvFromSource {
                return EnvFromSource(
                    configMapRef = configMapRef,
                    prefix = prefix,
                    secretRef = secretRef
                )
            }
        }
    }

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
        ) {
            class Builder : DslBuilder<Capabilities> {
                private var add: List<String>? = null
                private var drop: List<String>? = null
                override fun build(): Capabilities {
                    return Capabilities(
                        add = add,
                        drop = drop
                    )
                }
            }
        }

        class Builder : DslBuilder<SecurityContext> {
            private var allowPrivilegeEscalation: Boolean? = null
            private var appArmorProfile: AppArmorProfile? = null
            private var capabilities: Capabilities? = null
            private var procMount: String? = null
            private var privileged: Boolean? = null
            private var readOnlyRootFilesystem: Boolean? = null
            private var runAsUser: Long? = null
            private var runAsNonRoot: Boolean? = null
            private var runAsGroup: Long? = null
            private var seLinuxOptions: SELinuxOptions? = null
            private var seccompProfile: SeccompProfile? = null
            private var windowsOptions: WindowsSecurityContextOptions? = null

            override fun build(): SecurityContext {
                return SecurityContext(
                    allowPrivilegeEscalation = allowPrivilegeEscalation,
                    appArmorProfile = appArmorProfile,
                    capabilities = capabilities,
                    procMount = procMount,
                    privileged = privileged,
                    readOnlyRootFilesystem = readOnlyRootFilesystem,
                    runAsUser = runAsUser,
                    runAsNonRoot = runAsNonRoot,
                    runAsGroup = runAsGroup,
                    seLinuxOptions = seLinuxOptions,
                    seccompProfile = seccompProfile,
                    windowsOptions = windowsOptions
                )
            }
        }
    }

    data class ResourceMetricSource(
        val container: String,
        val name: String,
        val target: Metric.Target? = null
    ) : BaseResourceMetricSource {
        class Builder : DslBuilder<ResourceMetricSource> {
            private var container: String? = null
            private var name: String? = null
            private var target: Metric.Target? = null

            override fun build(): ResourceMetricSource {
                return ResourceMetricSource(
                    container = container ?: error("container is required"),
                    name = name ?: error("name is required"),
                    target = target
                )
            }
        }
    }

    data class ResourceMetricStatus(
        val container: String,
        val current: Metric.ValueStatus,
        val name: String
    ) : BaseResourceMetricStatus {
        class Builder : DslBuilder<ResourceMetricStatus> {
            private var container: String? = null
            private var current: Metric.ValueStatus? = null
            private var name: String? = null

            override fun build(): ResourceMetricStatus {
                return ResourceMetricStatus(
                    container = container ?: error("container is required"),
                    current = current ?: error("current is required"),
                    name = name ?: error("name is required")
                )
            }
        }
    }
}