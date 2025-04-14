package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.k8sResources.workload.pod.affinity.Affinity
import io.violabs.picard.domain.k8sResources.workload.pod.container.*
import io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig.DNSConfig
import io.violabs.picard.domain.k8sResources.workload.pod.gate.ReadinessGate
import io.violabs.picard.domain.k8sResources.workload.pod.gate.ReadinessGateGroup
import io.violabs.picard.domain.k8sResources.workload.pod.gate.SchedulingGate
import io.violabs.picard.domain.k8sResources.workload.pod.gate.SchedulingGateGroup
import io.violabs.picard.domain.k8sResources.workload.pod.hostAlias.HostAlias
import io.violabs.picard.domain.k8sResources.workload.pod.hostAlias.HostAliasGroup
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaim
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaimGroup
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaimStatus
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaimStatusGroup
import io.violabs.picard.domain.k8sResources.workload.pod.security.PodSecurityContext
import java.time.LocalDateTime

data class Pod(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Pod.Version>, Kind {

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
            private var _containers: MutableList<Container>? = null
            private var _initContainers: MutableList<Container>? = null
            private var _ephemeralContainers: MutableList<EphemeralContainer>? = null
            private var _imagePullSecrets: MutableList<LocalObjectReference>? = null
            private var enableServiceLinks: Boolean? = null
            private var os: PodOS? = null
            private var _volumes: MutableList<Volume>? = null
            private var _nodeSelector: MutableMap<String, String>? = null
            var nodeName: String? = null
            private var _affinity: Affinity? = null
            private var _tolerations: MutableList<Toleration>? = null
            var schedulerName: String? = null
            var runtimeClassName: String? = null
            var priorityClassName: String? = null
            var priority: Int? = null
            var preemptionPolicy: String? = null
            private var _topologySpreadConstraints: MutableList<TopologySpreadConstraint>? = null
            private var _overhead: MutableMap<String, Quantity>? = null
            var restartPolicy: RestartPolicy? = null
            var terminationGracePeriodSeconds: Long? = null
            var activeDeadlineSeconds: Long? = null
            private var _readinessGates: MutableList<ReadinessGate>? = null
            var hostname: String? = null
            private var setHostnameAsFQDN: Boolean? = null
            var subdomain: String? = null
            private var _hostAliases: MutableList<HostAlias>? = null
            private var _dnsConfig: DNSConfig? = null
            private var hostNetwork: Boolean? = null
            private var hostPID: Boolean? = null
            private var hostIPC: Boolean? = null
            private var shareProcessNamespace: Boolean? = null
            var serviceAccountName: String? = null
            private var automountServiceAccountToken: Boolean? = null
            private var _securityContext: PodSecurityContext? = null
            private var hostUsers: Boolean? = null
            private var _resourceClaims: MutableList<PodResourceClaim>? = null
            private var _schedulingGates: MutableList<SchedulingGate>? = null

            fun containers(): MutableList<Container>? {
                return _containers
            }

            fun containers(scope: BaseContainerGroup<Container, Container.Builder>.() -> Unit) {
                _containers = BaseContainerGroup(Container.Builder()).apply(scope).containers()
            }

            fun initContainers(scope: BaseContainerGroup<Container, Container.Builder>.() -> Unit) {
                _initContainers = BaseContainerGroup(Container.Builder()).apply(scope).containers()
            }

            fun ephemeralContainers(scope: BaseContainerGroup<EphemeralContainer, EphemeralContainer.Builder>.() -> Unit) {
                _ephemeralContainers = BaseContainerGroup(EphemeralContainer.Builder()).apply(scope).containers()
            }

            fun imagePullSecrets(scope: LocalObjectReferenceGroup.() -> Unit) {
                _imagePullSecrets = LocalObjectReferenceGroup().apply(scope).references()
            }

            fun enableServiceLinks(value: Boolean = true) {
                enableServiceLinks = value
            }

            fun os(name: String) {
                os = PodOS(name)
            }

            fun volumes(scope: VolumeGroup.() -> Unit) {
                _volumes = VolumeGroup().apply(scope).volumes()
            }

            fun nodeSelector(scope: MutableMap<String, String>.() -> Unit) {
                _nodeSelector = mutableMapOf<String, String>().apply(scope)
            }

            fun affinity(scope: Affinity.Builder.() -> Unit) {
                _affinity = Affinity.Builder().apply(scope).build()
            }

            fun tolerations(scope: TolerationGroup.() -> Unit) {
                _tolerations = TolerationGroup().apply(scope).tolerations()
            }

            fun topologySpreadConstraints(scope: TopologySpreadConstraintGroup.() -> Unit) {
                _topologySpreadConstraints = TopologySpreadConstraintGroup().apply(scope).constraints()
            }

            fun overhead(scope: MutableMap<String, Quantity>.() -> Unit) {
                _overhead = mutableMapOf<String, Quantity>().apply(scope)
            }

            fun readinessGates(scope: ReadinessGateGroup.() -> Unit) {
                _readinessGates = ReadinessGateGroup().apply(scope).readinessGates()
            }

            fun setHostnameAsFQDN(value: Boolean = true) {
                setHostnameAsFQDN = value
            }

            fun hostAliases(scope: HostAliasGroup.() -> Unit) {
                _hostAliases = HostAliasGroup().apply(scope).hostAliases()
            }

            fun dnsConfig(scope: DNSConfig.Builder.() -> Unit) {
                _dnsConfig = DNSConfig.Builder().apply(scope).build()
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
                _securityContext = PodSecurityContext.Builder().apply(scope).build()
            }

            fun hostUsers(value: Boolean = true) {
                hostUsers = value
            }

            fun resourceClaims(scope: PodResourceClaimGroup.() -> Unit) {
                _resourceClaims = PodResourceClaimGroup().apply(scope).resourceClaims()
            }

            fun schedulingGates(scope: SchedulingGateGroup.() -> Unit) {
                _schedulingGates = SchedulingGateGroup().apply(scope).schedulingGates()
            }

            override fun build(): Spec {
                return Spec(
                    vRequireNotEmpty(this::containers),
                    initContainers = _initContainers,
                    ephemeralContainers = _ephemeralContainers,
                    imagePullSecrets = _imagePullSecrets,
                    enableServiceLinks = enableServiceLinks,
                    os = os,
                    volumes = _volumes,
                    nodeSelector = _nodeSelector,
                    nodeName = nodeName,
                    affinity = _affinity,
                    tolerations = _tolerations,
                    schedulerName = schedulerName,
                    runtimeClassName = runtimeClassName,
                    priorityClassName = priorityClassName,
                    priority = priority,
                    preemptionPolicy = preemptionPolicy,
                    topologySpreadConstraints = _topologySpreadConstraints,
                    overhead = _overhead,
                    restartPolicy = restartPolicy,
                    terminationGracePeriodSeconds = terminationGracePeriodSeconds,
                    activeDeadlineSeconds = activeDeadlineSeconds,
                    readinessGates = _readinessGates,
                    hostname = hostname,
                    setHostnameAsFQDN = setHostnameAsFQDN,
                    subdomain = subdomain,
                    hostAliases = _hostAliases,
                    dnsConfig = _dnsConfig,
                    hostNetwork = hostNetwork,
                    hostPID = hostPID,
                    hostIPC = hostIPC,
                    shareProcessNamespace = shareProcessNamespace,
                    serviceAccountName = serviceAccountName,
                    automountServiceAccountToken = automountServiceAccountToken,
                    securityContext = _securityContext,
                    hostUsers = hostUsers,
                    resourceClaims = _resourceClaims,
                    schedulingGates = _schedulingGates
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

            fun conditions(scope: ConditionGroup<Condition, Condition.Builder>.() -> Unit) {
                conditions = ConditionGroup(Condition.Builder()).apply(scope).conditions()
            }

            fun initContainerStatuses(scope: ContainerStatusGroup.() -> Unit) {
                initContainerStatuses = ContainerStatusGroup().apply(scope).statuses()
            }

            fun containerStatuses(scope: ContainerStatusGroup.() -> Unit) {
                containerStatuses = ContainerStatusGroup().apply(scope).statuses()
            }

            fun ephemeralContainerStatuses(scope: ContainerStatusGroup.() -> Unit) {
                ephemeralContainerStatuses = ContainerStatusGroup().apply(scope).statuses()
            }

            fun resourceClaimStatuses(scope: PodResourceClaimStatusGroup.() -> Unit) {
                resourceClaimStatuses = PodResourceClaimStatusGroup().apply(scope).statuses()
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

    class Builder : DslBuilder<Pod> {
        private var metadata: ObjectMetadata? = null
        private var spec: Spec? = null
        private var status: Status? = null

        fun metadata(init: ObjectMetadata.Builder.() -> Unit) {
            metadata = ObjectMetadata.Builder().apply(init).build()
        }

        fun spec(init: Spec.Builder.() -> Unit) {
            spec = Spec.Builder().apply(init).build()
        }

        fun status(scope: Status.Builder.() -> Unit) {
            this.status = Status.Builder().apply(scope).build()
        }

        override fun build(): Pod {
            return Pod(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }
}