package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.manifest.ServiceResource

data class Service(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : ServiceResource<Service.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val selector: Map<String, String>? = null,
        val ports: List<ServicePort>? = null,
        val type: Type? = null,
        val ipFamilies: List<String>? = null,
        val ipFamilyPolicy: String? = null,
        val clusterIP: String? = null,
        val clusterIPs: List<String>? = null,
        val externalIPs: List<String>? = null,
        val sessionAffinity: String? = null,
        val loadBalancerIP: String? = null,
        val loadBalancerSourceRanges: List<String>? = null,
        val loadBalancerClass: String? = null,
        val externalName: String? = null,
        val externalTrafficPolicy: String? = null,
        val internalTrafficPolicy: String? = null,
        val healthCheckNodePort: Int? = null,
        val publishNotReadyAddresses: Boolean? = null,
        val sessionAffinityConfig: SessionAffinityConfig? = null,
        val allocateLoadBalancerNodePorts: Boolean? = null,
        val trafficDistribution: String? = null
    ) : BaseSpec {
        enum class Type {
            ExternalName,
            ClusterIP,
            NodePort,
            LoadBalancer
        }

        class Builder : DslBuilder<Spec> {
            private var selector: Map<String, String>? = null
            private var ports: List<ServicePort>? = null
            var type: Type? = null
            private var ipFamilies: List<String>? = null
            var ipFamilyPolicy: String? = null
            var clusterIP: String? = null
            private var clusterIPs: List<String>? = null
            private var externalIPs: List<String>? = null
            var sessionAffinity: String? = null
            var loadBalancerIP: String? = null
            private var loadBalancerSourceRanges: List<String>? = null
            var loadBalancerClass: String? = null
            var externalName: String? = null
            var externalTrafficPolicy: String? = null
            var internalTrafficPolicy: String? = null
            var healthCheckNodePort: Int? = null
            private var publishNotReadyAddresses: Boolean? = null
            private var sessionAffinityConfig: SessionAffinityConfig? = null
            private var allocateLoadBalancerNodePorts: Boolean? = null
            var trafficDistribution: String? = null

            fun selector(vararg selectors: Pair<String, String>) {
                selector = selectors.toMap()
            }

            fun ports(scope: ServicePort.Group.() -> Unit) {
                ports = ServicePort.Group().apply(scope).ports()
            }

            fun ipFamilies(vararg families: String) {
                ipFamilies = families.toList()
            }

            fun clusterIPs(vararg ips: String) {
                clusterIPs = ips.toList()
            }

            fun externalIPs(vararg ips: String) {
                externalIPs = ips.toList()
            }

            fun loadBalancerSourceRanges(vararg ranges: String) {
                loadBalancerSourceRanges = ranges.toList()
            }

            fun publishNotReadyAddresses(value: Boolean = true) {
                publishNotReadyAddresses = value
            }

            fun sessionAffinityConfig(scope: SessionAffinityConfig.Builder.() -> Unit) {
                sessionAffinityConfig = SessionAffinityConfig.Builder().apply(scope).build()
            }

            fun allocateLoadBalancerNodePorts(value: Boolean = true) {
                allocateLoadBalancerNodePorts = value
            }

            override fun build(): Spec {
                return Spec(
                    selector = selector,
                    ports = ports,
                    type = type,
                    ipFamilies = ipFamilies,
                    ipFamilyPolicy = ipFamilyPolicy,
                    clusterIP = clusterIP,
                    clusterIPs = clusterIPs,
                    externalIPs = externalIPs,
                    sessionAffinity = sessionAffinity,
                    loadBalancerIP = loadBalancerIP,
                    loadBalancerSourceRanges = loadBalancerSourceRanges,
                    loadBalancerClass = loadBalancerClass,
                    externalName = externalName,
                    externalTrafficPolicy = externalTrafficPolicy,
                    internalTrafficPolicy = internalTrafficPolicy,
                    healthCheckNodePort = healthCheckNodePort,
                    publishNotReadyAddresses = publishNotReadyAddresses,
                    sessionAffinityConfig = sessionAffinityConfig,
                    allocateLoadBalancerNodePorts = allocateLoadBalancerNodePorts,
                    trafficDistribution = trafficDistribution
                )
            }
        }
    }

    data class Status(
        val loadBalancer: LoadBalancerStatus? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var loadBalancer: LoadBalancerStatus? = null

            fun loadBalancer(scope: LoadBalancerStatus.Builder.() -> Unit) {
                loadBalancer = LoadBalancerStatus.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    loadBalancer = loadBalancer
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        Service,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): Service {
            return Service(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Service, Builder>(Builder()) {
        fun serviceItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}