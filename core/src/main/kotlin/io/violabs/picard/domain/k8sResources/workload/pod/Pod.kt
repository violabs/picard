package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.k8sResources.workload.*
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.EphemeralContainer
import io.violabs.picard.domain.k8sResources.workload.pod.security.*
import java.time.LocalDateTime

data class Pod(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Pod.Version> {

    interface Version  : APIVersion

    data class Spec(
        // Containers
        val containers: List<Container>,
        val initContainers: List<Container>? = null,
        val ephemeralContainers: List<EphemeralContainer>? = null,
        val imagePullSecrets: List<LocalObjectReference>? = null,
        val enableServiceLinks: Boolean? = null,
        val os: OS? = null,
        // Volumes
        val volumes: List<Volume>? = null,
        // Scheduling
        val nodeSelector: Map<String, String>? = null,
        val nodeName: String? = null,
        val affinity: Affinity? = null,
        val tolerations: List<Toleration>? = null,
        val schedulerName: String? = null,
        val runtimeClassName: String? = null,
        val priorityClassName: String? = null,
        val priority: Int? = null,
        val preemptionPolicy: String? = null,
        val topologySpreadConstraints: List<TopologySpreadConstraint>? = null,
        val overhead: Map<String, Quantity>? = null,
        // Lifecycle
        val restartPolicy: RestartPolicy? = null,
        val terminationGracePeriodSeconds: Long? = null,
        val activeDeadlineSeconds: Long? = null,
        val readinessGates: List<ReadinessGate>? = null,
        // Hostname and Name Resolution
        val hostname: String? = null,
        val setHostnameAsFQDN: Boolean? = null,
        val subdomain: String? = null,
        val hostAliases: List<HostAlias>? = null,
        val dnsConfig: DNSConfig? = null,
        // Hosts Namespaces
        val hostNetwork: Boolean? = null,
        val hostPID: Boolean? = null,
        val hostIPC: Boolean? = null,
        val shareProcessNamespace: Boolean? = null,
        // Service Account
        val serviceAccountName: String? = null,
        val automountServiceAccountToken: Boolean? = null,
        // Security Context
        val securityContext: SecurityContext? = null,
        // Alpha Level
        val hostUsers: Boolean? = null,
        val resourceClaims: List<ResourceClaim>? = null,
        val schedulingGates: List<SchedulingGates>? = null,
    ) : BaseSpec

    data class OS(
        val name: String
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
        val options: List<Option>? = null,
        val searches: List<String>? = null,
        val dnsPolicy: String? = null
    ) {
        data class Option(val name: String, val value: String? = null)
    }

    data class ResourceClaim(
        val name: String,
        val resourceClaimName: String? = null,
        val resourceClaimTemplateName: String? = null
    ) : BaseResourceClaim {
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
        val sysctls: List<Sysctl>? = null,
        val windowsOptions: WindowsSecurityContextOptions? = null
    ) : BaseSecurityContext {
        data class Sysctl(
            val name: String,
            val value: String
        )
    }

    data class Status(
        val nominatedNodeName: String? = null,
        val hostIP: String? = null,
        val hostIPs: List<HostIP>? = null,
        val startTime: LocalDateTime? = null,
        val phase: String? = null,
        val message: String? = null,
        val reason: String? = null,
        val podIP: String? = null,
        val podIPs: List<PodIP>? = null,
        val conditions: List<Condition>? = null,
        val qosClass: String? = null,
        val initContainerStatuses: List<Container.Status>? = null,
        val containerStatuses: List<Container.Status>? = null,
        val ephemeralContainerStatuses: List<Container.Status>? = null,
        val resourceClaimStatuses: List<ResourceClaim.Status>? = null,
        val resize: String? = null
    ) : BaseStatus {
        data class HostIP(val ip: String)
        data class PodIP(val ip: String)
    }

    data class FailurePolicy(val rules: List<Rule>) {
        data class Rule(
            val action: Action,
            val onExitCods: OnExitCodesRequirement? = null,
            val onPodConditions: List<OnPodConditionsPattern>? = null
        ) {
            enum class Action : K8sEnum {
                COUNT,
                FAIL_INDEX,
                FAIL_JOB,
                IGNORE
            }
        }

        data class OnExitCodesRequirement(
            val operator: Operator,
            val values: List<Int>,
            val containerName: String? = null
        ) {
            enum class Operator : K8sEnum {
                IN,
                NOT_IN;

                override fun toString() = properCase()
            }
        }

        data class OnPodConditionsPattern(
            val status: BooleanType,
            val type: String
        )
    }

    data class SuccessPolicy(
        val rules: List<Rule>? = null
    ) {
        data class Rule(
            val succeededCount: Int? = null,
            val succeededIndexes: String? = null
        )
    }
}