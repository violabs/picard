package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.EphemeralContainer
import io.violabs.picard.domain.k8sResources.workload.pod.security.*
import java.time.LocalDateTime

data class Pod(
    override val apiVersion: io.violabs.picard.domain.k8sResources.workload.pod.Pod.Version,
    val metadata: ObjectMetadata? = null,
    val spec: io.violabs.picard.domain.k8sResources.workload.pod.Pod.Spec? = null,
    val status: io.violabs.picard.domain.k8sResources.workload.pod.Pod.Status? = null
) : K8sResource<io.violabs.picard.domain.k8sResources.workload.pod.Pod.Version> {
    override val kind: Kind = Kind.POD

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }

    data class Spec(
        // Containers
        val containers: List<Container>,
        val initContainers: List<Container>? = null,
        val ephemeralContainers: List<EphemeralContainer>? = null,
        val imagePullSecrets: List<LocalObjectReference>? = null,
        val enableServiceLinks: Boolean? = null,
        val os: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.OS? = null,
        // Volumes
        val volumes: List<Volume>? = null,
        // Scheduling
        val nodeSelector: Map<String, String>? = null,
        val nodeName: String? = null,
        val affinity: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Affinity? = null,
        val tolerations: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Toleration>? = null,
        val schedulerName: String? = null,
        val runtimeClassName: String? = null,
        val priorityClassName: String? = null,
        val priority: Int? = null,
        val preemptionPolicy: String? = null,
        val topologySpreadConstraints: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.TopologySpreadConstraint>? = null,
        val overhead: Map<String, Quantity>? = null,
        // Lifecycle
        val restartPolicy: RestartPolicy? = null,
        val terminationGracePeriodSeconds: Long? = null,
        val activeDeadlineSeconds: Long? = null,
        val readinessGates: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.ReadinessGate>? = null,
        // Hostname and Name Resolution
        val hostname: String? = null,
        val setHostnameAsFQDN: Boolean? = null,
        val subdomain: String? = null,
        val hostAliases: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.HostAlias>? = null,
        val dnsConfig: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.DNSConfig? = null,
        // Hosts Namespaces
        val hostNetwork: Boolean? = null,
        val hostPID: Boolean? = null,
        val hostIPC: Boolean? = null,
        val shareProcessNamespace: Boolean? = null,
        // Service Account
        val serviceAccountName: String? = null,
        val automountServiceAccountToken: Boolean? = null,
        // Security Context
        val securityContext: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.SecurityContext? = null,
        // Alpha Level
        val hostUsers: Boolean? = null,
        val resourceClaims: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.ResourceClaim>? = null,
        val schedulingGates: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.SchedulingGates>? = null,

        //>>
        val template: PodTemplate? = null,
        val ports: List<ServicePortConfig>? = null,
        val replicas: Int? = null,
        val selector: Selector? = null,
        val strategy: Strategy? = null
    )

    data class OS(
        val name: String
    )

    data class Toleration(
        val key: String? = null,
        val operator: String? = null,
        val value: String? = null,
        val effect: String? = null,
        val tolerationSeconds: Long? = null
    )

    data class TopologySpreadConstraint(
        val maxSkew: Int,
        val topologyKey: String,
        val whenUnsatisfiable: String,
        val labelSelector: LabelSelector? = null,
        val matchLabelKeys: List<String>? = null,
        val minDomains: Int? = null,
        val nodeAffinityPolicy: String? = null,
        val nodeTaintsPolicy: String? = null
    )

    data class HostAlias(val ip: String, val hostnames: List<String>? = null)

    data class DNSConfig(
        val nameservers: List<String>? = null,
        val options: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.DNSConfig.Option>? = null,
        val searches: List<String>? = null,
        val dnsPolicy: String? = null
    ) {
        data class Option(val name: String, val value: String? = null)
    }

    data class ResourceClaim(
        val name: String,
        val resourceClaimName: String? = null,
        val resourceClaimTemplateName: String? = null
    ) {
        data class Status(
            val name: String,
            val resourceClaimName: String? = null
        )
    }

    data class SchedulingGates(val name: String)

    data class SecurityContext(
        val appArmorProfile: AppArmorProfile? = null,
        val fsGroup: Long? = null,
        val fsGroupChangePolicy: String? = null,
        val runAsUser: Long? = null,
        val runAsNonRoot: Boolean? = null,
        val runAsGroup: Long? = null,
        val seccompProfile: SeccompProfile? = null,
        val seLinuxOptions: SELinuxOptions? = null,
        val supplementalGroups: List<Long>? = null,
        val supplementalGroupsPolicy: String? = null,
        val sysctls: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.SecurityContext.Sysctl>? = null,
        val windowsOptions: WindowsSecurityContextOptions? = null
    ) {
        data class Sysctl(
            val name: String,
            val value: String
        )
    }

    data class Status(
        val nominatedNodeName: String? = null,
        val hostIP: String? = null,
        val hostIPs: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Status.HostIP>? = null,
        val startTime: LocalDateTime? = null,
        val phase: String? = null,
        val message: String? = null,
        val reason: String? = null,
        val podIP: String? = null,
        val podIPs: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Status.PodIP>? = null,
        val conditions: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Condition>? = null,
        val qosClass: String? = null,
        val initContainerStatuses: List<Container.Status>? = null,
        val containerStatuses: List<Container.Status>? = null,
        val ephemeralContainerStatuses: List<Container.Status>? = null,
        val resourceClaimStatuses: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.ResourceClaim.Status>? = null,
        val resize: String? = null
    ) {
        data class HostIP(val ip: String)
        data class PodIP(val ip: String)
    }

    data class Condition(
        val status: _root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod.Condition.Status,
        val type: String,
        val lastProbeTime: LocalDateTime? = null,
        val lastTransitionTime: LocalDateTime? = null,
        val message: String? = null,
        val reason: String? = null
    ) {
        enum class Status : K8sEnum {
            TRUE,
            FALSE,
            UNKNOWN;

            override fun toString(): String = properCase()
        }
    }
}