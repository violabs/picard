package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

// Minimal stub implementations for container dependencies

@GeneratedDsl
data class ConfigMapKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
)

@GeneratedDsl
data class ObjectFieldSelector(
    val fieldPath: String,
    val apiVersion: String? = null
)

@GeneratedDsl
data class ResourceFieldSelector(
    val resource: String,
    val containerName: String? = null,
    val divisor: Quantity? = null
)

@GeneratedDsl
data class SecretKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
)

@GeneratedDsl
data class ConfigMapEnvSource(
    val name: String? = null,
    val optional: Boolean? = null
)

@GeneratedDsl
data class SecretEnvSource(
    val name: String? = null,
    val optional: Boolean? = null
)

@GeneratedDsl
data class VolumeMount(
    val name: String,
    val mountPath: String,
    val readOnly: Boolean? = null,
    val subPath: String? = null,
    val subPathExpr: String? = null,
    val mountPropagation: String? = null
)

@GeneratedDsl
data class VolumeDevice(
    val name: String,
    val devicePath: String
)

@GeneratedDsl
data class ResourceRequirements(
    val claims: List<ResourceClaim>? = null,
    val limits: Map<String, Quantity>? = null,
    val requests: Map<String, Quantity>? = null
)

@GeneratedDsl
data class ResourceClaim(
    val name: String,
    val request: String? = null
)

@GeneratedDsl
data class Lifecycle(
    val postStart: LifecycleHandler? = null,
    val preStop: LifecycleHandler? = null
)

@GeneratedDsl
data class LifecycleHandler(
    val exec: ExecAction? = null,
    val httpGet: HttpGetAction? = null,
    val sleep: SleepAction? = null,
    val tcpSocket: TcpSocketAction? = null
)

@GeneratedDsl
data class ExecAction(
    val command: List<String>? = null
)

@GeneratedDsl
data class HttpGetAction(
    val path: String? = null,
    val port: String? = null,
    val host: String? = null,
    val scheme: String? = null,
    val httpHeaders: List<HttpHeader>? = null
)

@GeneratedDsl
data class HttpHeader(
    val name: String,
    val value: String
)

@GeneratedDsl
data class SleepAction(
    val seconds: Long
)

@GeneratedDsl
data class TcpSocketAction(
    val port: String,
    val host: String? = null
)

@GeneratedDsl
data class Probe(
    val exec: ExecAction? = null,
    val httpGet: HttpGetAction? = null,
    val tcpSocket: TcpSocketAction? = null,
    val grpc: GrpcAction? = null,
    val initialDelaySeconds: Int? = null,
    val timeoutSeconds: Int? = null,
    val periodSeconds: Int? = null,
    val successThreshold: Int? = null,
    val failureThreshold: Int? = null,
    val terminationGracePeriodSeconds: Long? = null
)

@GeneratedDsl
data class GrpcAction(
    val port: Int,
    val service: String? = null
)

@GeneratedDsl
data class ContainerSecurityContext(
    val allowPrivilegeEscalation: Boolean? = null,
    val appArmorProfile: AppArmorProfile? = null,
    val capabilities: Capabilities? = null,
    val privileged: Boolean? = null,
    val procMount: String? = null,
    val readOnlyRootFilesystem: Boolean? = null,
    val runAsGroup: Long? = null,
    val runAsNonRoot: Boolean? = null,
    val runAsUser: Long? = null,
    val seLinuxOptions: SeLinuxOptions? = null,
    val seccompProfile: SeccompProfile? = null,
    val windowsOptions: WindowsSecurityContextOptions? = null
)

@GeneratedDsl
data class AppArmorProfile(
    val type: String,
    val localhostProfile: String? = null
)

@GeneratedDsl
data class Capabilities(
    val add: List<String>? = null,
    val drop: List<String>? = null
)

@GeneratedDsl
data class SeLinuxOptions(
    val level: String? = null,
    val role: String? = null,
    val type: String? = null,
    val user: String? = null
)

@GeneratedDsl
data class SeccompProfile(
    val type: String,
    val localhostProfile: String? = null
)

@GeneratedDsl
data class WindowsSecurityContextOptions(
    val gmsaCredentialSpec: String? = null,
    val gmsaCredentialUser: String? = null,
    val hostProcess: Boolean? = null,
    val runAsUserName: String? = null
)

@GeneratedDsl
data class ContainerState(
    val running: ContainerStateRunning? = null,
    val terminated: ContainerStateTerminated? = null,
    val waiting: ContainerStateWaiting? = null
)

@GeneratedDsl
data class ContainerStateRunning(
    val startedAt: java.time.LocalDateTime? = null
)

@GeneratedDsl
data class ContainerStateTerminated(
    val containerID: String? = null,
    val exitCode: Int,
    val finishedAt: java.time.LocalDateTime? = null,
    val message: String? = null,
    val reason: String? = null,
    val signal: Int? = null,
    val startedAt: java.time.LocalDateTime? = null
)

@GeneratedDsl
data class ContainerStateWaiting(
    val message: String? = null,
    val reason: String? = null
)