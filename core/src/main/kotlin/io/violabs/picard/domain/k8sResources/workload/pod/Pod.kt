package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.*
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.k8sResources.workload.pod.affinity.Affinity
import io.violabs.picard.domain.k8sResources.workload.pod.container.*
import io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig.DNSConfig
import io.violabs.picard.domain.k8sResources.workload.pod.gate.ReadinessGate
import io.violabs.picard.domain.k8sResources.workload.pod.gate.SchedulingGate
import io.violabs.picard.domain.k8sResources.workload.pod.hostAlias.HostAlias
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaim
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaimStatus
import io.violabs.picard.domain.k8sResources.workload.pod.security.PodSecurityContext
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.resources.storage.volume.Volume
import io.violabs.picard.v2.resources.storage.volume.VolumeDslBuilder
import java.time.LocalDateTime

data class Pod(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : WorkloadResource<Pod.Version, ObjectMetadata> {

    interface Version : APIVersion

    data class Spec(
        // Containers
        val containers: List<Container>,
        val initContainers: List<Container>? = null,
        val ephemeralContainers: List<EphemeralContainer>? = null,
        val imagePullSecrets: List<LocalObjectReference>? = null,
        val enableServiceLinks: Boolean? = null,
        val os: PodOS? = null,
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
        val securityContext: PodSecurityContext? = null,
        // Alpha Level
        val hostUsers: Boolean? = null,
        val resourceClaims: List<PodResourceClaim>? = null,
        val schedulingGates: List<SchedulingGate>? = null,
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var containers: List<Container>? = null
            private var initContainers: List<Container>? = null
            private var ephemeralContainers: List<EphemeralContainer>? = null
            private var imagePullSecrets: List<LocalObjectReference>? = null
            private var enableServiceLinks: Boolean? = null
            private var os: PodOS? = null
            private var volumes: List<Volume>? = null
            private var nodeSelector: MutableMap<String, String>? = null
            var nodeName: String? = null
            private var affinity: Affinity? = null
            private var tolerations: List<Toleration>? = null
            var schedulerName: String? = null
            var runtimeClassName: String? = null
            var priorityClassName: String? = null
            var priority: Int? = null
            var preemptionPolicy: String? = null
            private var topologySpreadConstraints: List<TopologySpreadConstraint>? = null
            private var overhead: MutableMap<String, Quantity>? = null
            var restartPolicy: RestartPolicy? = null
            var terminationGracePeriodSeconds: Long? = null
            var activeDeadlineSeconds: Long? = null
            private var readinessGates: List<ReadinessGate>? = null
            var hostname: String? = null
            private var setHostnameAsFQDN: Boolean? = null
            var subdomain: String? = null
            private var hostAliases: List<HostAlias>? = null
            private var dnsConfig: DNSConfig? = null
            private var hostNetwork: Boolean? = null
            private var hostPID: Boolean? = null
            private var hostIPC: Boolean? = null
            private var shareProcessNamespace: Boolean? = null
            var serviceAccountName: String? = null
            private var automountServiceAccountToken: Boolean? = null
            private var securityContext: PodSecurityContext? = null
            private var hostUsers: Boolean? = null
            private var resourceClaims: List<PodResourceClaim>? = null
            private var schedulingGates: List<SchedulingGate>? = null

            fun containers(scope: BaseContainerGroup<Container, Container.Builder>.() -> Unit) {
                containers = BaseContainerGroup(Container.Builder()).apply(scope).containers()
            }

            fun initContainers(scope: BaseContainerGroup<Container, Container.Builder>.() -> Unit) {
                initContainers = BaseContainerGroup(Container.Builder()).apply(scope).containers()
            }

            fun ephemeralContainers(scope: BaseContainerGroup<EphemeralContainer, EphemeralContainer.Builder>.() -> Unit) {
                ephemeralContainers = BaseContainerGroup(EphemeralContainer.Builder()).apply(scope).containers()
            }

            fun imagePullSecrets(scope: LocalObjectReference.Group.() -> Unit) {
                imagePullSecrets = LocalObjectReference.Group().apply(scope).references()
            }

            fun enableServiceLinks(value: Boolean = true) {
                enableServiceLinks = value
            }

            fun os(name: String) {
                os = PodOS(name)
            }

            fun volumes(scope: VolumeDslBuilder.Group.() -> Unit) {
                volumes = VolumeDslBuilder.Group().apply(scope).items()
            }

            fun nodeSelector(scope: MutableMap<String, String>.() -> Unit) {
                nodeSelector = mutableMapOf<String, String>().apply(scope)
            }

            fun affinity(scope: Affinity.Builder.() -> Unit) {
                affinity = Affinity.Builder().apply(scope).build()
            }

            fun tolerations(scope: TolerationGroup.() -> Unit) {
                tolerations = TolerationGroup().apply(scope).tolerations()
            }

            fun topologySpreadConstraints(scope: TopologySpreadConstraintGroup.() -> Unit) {
                topologySpreadConstraints = TopologySpreadConstraintGroup().apply(scope).constraints()
            }

            fun overhead(scope: MutableMap<String, Quantity>.() -> Unit) {
                overhead = mutableMapOf<String, Quantity>().apply(scope)
            }

            fun readinessGates(scope: ReadinessGate.Group.() -> Unit) {
                readinessGates = ReadinessGate.Group().apply(scope).readinessGates()
            }

            fun setHostnameAsFQDN(value: Boolean = true) {
                setHostnameAsFQDN = value
            }

            fun hostAliases(scope: HostAlias.Group.() -> Unit) {
                hostAliases = HostAlias.Group().apply(scope).hostAliases()
            }

            fun dnsConfig(scope: DNSConfig.Builder.() -> Unit) {
                dnsConfig = DNSConfig.Builder().apply(scope).build()
            }

            fun hostNetwork(value: Boolean = true) {
                hostNetwork = value
            }

            fun hostPID(value: Boolean = true) {
                hostPID = value
            }

            fun hostIPC(value: Boolean = true) {
                hostIPC = value
            }

            fun shareProcessNamespace(value: Boolean = true) {
                shareProcessNamespace = value
            }

            fun automountServiceAccountToken(value: Boolean = true) {
                automountServiceAccountToken = value
            }

            fun securityContext(scope: PodSecurityContext.Builder.() -> Unit) {
                securityContext = PodSecurityContext.Builder().apply(scope).build()
            }

            fun hostUsers(value: Boolean = true) {
                hostUsers = value
            }

            fun resourceClaims(scope: PodResourceClaim.Group.() -> Unit) {
                resourceClaims = PodResourceClaim.Group().apply(scope).resourceClaims()
            }

            fun schedulingGates(scope: SchedulingGate.Group.() -> Unit) {
                schedulingGates = SchedulingGate.Group().apply(scope).gates()
            }

            override fun build(): Spec {
                return Spec(
                    vRequireNotEmpty(this::containers),
                    initContainers = initContainers,
                    ephemeralContainers = ephemeralContainers,
                    imagePullSecrets = imagePullSecrets,
                    enableServiceLinks = enableServiceLinks,
                    os = os,
                    volumes = volumes,
                    nodeSelector = nodeSelector,
                    nodeName = nodeName,
                    affinity = affinity,
                    tolerations = tolerations,
                    schedulerName = schedulerName,
                    runtimeClassName = runtimeClassName,
                    priorityClassName = priorityClassName,
                    priority = priority,
                    preemptionPolicy = preemptionPolicy,
                    topologySpreadConstraints = topologySpreadConstraints,
                    overhead = overhead,
                    restartPolicy = restartPolicy,
                    terminationGracePeriodSeconds = terminationGracePeriodSeconds,
                    activeDeadlineSeconds = activeDeadlineSeconds,
                    readinessGates = readinessGates,
                    hostname = hostname,
                    setHostnameAsFQDN = setHostnameAsFQDN,
                    subdomain = subdomain,
                    hostAliases = hostAliases,
                    dnsConfig = dnsConfig,
                    hostNetwork = hostNetwork,
                    hostPID = hostPID,
                    hostIPC = hostIPC,
                    shareProcessNamespace = shareProcessNamespace,
                    serviceAccountName = serviceAccountName,
                    automountServiceAccountToken = automountServiceAccountToken,
                    securityContext = securityContext,
                    hostUsers = hostUsers,
                    resourceClaims = resourceClaims,
                    schedulingGates = schedulingGates
                )
            }
        }

        class BaseContainerGroup<T : BaseContainer, B : DslBuilder<T>>(private val builder: B) {
            private var containers: MutableList<T> = mutableListOf()

            fun containers(): MutableList<T> {
                return containers
            }

            fun container(scope: B.() -> Unit) {
                containers.add(builder.apply(scope).build())
            }
        }

        class TolerationGroup {
            private var tolerations: MutableList<Toleration> = mutableListOf()
            fun tolerations(): MutableList<Toleration> {
                return tolerations
            }

            fun toleration(scope: Toleration.Builder.() -> Unit) {
                tolerations.add(Toleration.Builder().apply(scope).build())
            }
        }

        class TopologySpreadConstraintGroup {
            private var constraints: MutableList<TopologySpreadConstraint> = mutableListOf()
            fun constraints(): MutableList<TopologySpreadConstraint> {
                return constraints
            }

            fun constraint(scope: TopologySpreadConstraint.Builder.() -> Unit) {
                constraints.add(TopologySpreadConstraint.Builder().apply(scope).build())
            }
        }
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
        val initContainerStatuses: List<ContainerStatus>? = null,
        val containerStatuses: List<ContainerStatus>? = null,
        val ephemeralContainerStatuses: List<ContainerStatus>? = null,
        val resourceClaimStatuses: List<PodResourceClaimStatus>? = null,
        val resize: String? = null
    ) : BaseStatus {

        class Builder : DslBuilder<Status> {
            var nominatedNodeName: String? = null
            var hostIP: String? = null
            private var hostIPs: List<HostIP>? = null
            var startTime: LocalDateTime? = null
            var phase: String? = null
            var message: String? = null
            var reason: String? = null
            var podIP: String? = null
            private var podIPs: List<PodIP>? = null
            private var conditions: List<Condition>? = null
            var qosClass: String? = null
            private var initContainerStatuses: List<ContainerStatus>? = null
            private var containerStatuses: List<ContainerStatus>? = null
            private var ephemeralContainerStatuses: List<ContainerStatus>? = null
            private var resourceClaimStatuses: List<PodResourceClaimStatus>? = null
            var resize: String? = null

            fun hostIPs(scope: IPGroup<HostIP>.() -> Unit) {
                hostIPs = IPGroup(::HostIP).apply(scope).ips()
            }

            fun podIPs(scope: IPGroup<PodIP>.() -> Unit) {
                podIPs = IPGroup(::PodIP).apply(scope).ips()
            }

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            fun initContainerStatuses(scope: ContainerStatus.Group.() -> Unit) {
                initContainerStatuses = ContainerStatus.Group().apply(scope).statuses()
            }

            fun containerStatuses(scope: ContainerStatus.Group.() -> Unit) {
                containerStatuses = ContainerStatus.Group().apply(scope).statuses()
            }

            fun ephemeralContainerStatuses(scope: ContainerStatus.Group.() -> Unit) {
                ephemeralContainerStatuses = ContainerStatus.Group().apply(scope).statuses()
            }

            fun resourceClaimStatuses(scope: PodResourceClaimStatus.Group.() -> Unit) {
                resourceClaimStatuses = PodResourceClaimStatus.Group().apply(scope).resourceClaimStatuses()
            }

            override fun build(): Status {
                return Status(
                    nominatedNodeName = nominatedNodeName,
                    hostIP = hostIP,
                    hostIPs = hostIPs,
                    startTime = startTime,
                    phase = phase,
                    message = message,
                    reason = reason,
                    podIP = podIP,
                    podIPs = podIPs,
                    conditions = conditions,
                    qosClass = qosClass,
                    initContainerStatuses = initContainerStatuses,
                    containerStatuses = containerStatuses,
                    ephemeralContainerStatuses = ephemeralContainerStatuses,
                    resourceClaimStatuses = resourceClaimStatuses,
                    resize = resize
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        Pod,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder
        >(Spec.Builder(), Status.Builder()) {

        override fun build(): Pod {
            return Pod(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Pod, Builder>(Builder()) {
        fun podItem(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}