package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.k8sResources.workload.BaseLoadBalancerIngress
import io.violabs.picard.domain.k8sResources.workload.BasePort
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus

data class Service(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Service.Version> {
    interface Version : APIVersion

    data class Spec(
        val selector: Map<String, String>? = null,
        val ports: List<Port>? = null,
        val type: String? = null,
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
    ) : BaseSpec

    data class Status(
        val loadBalancer: LoadBalancerStatus? = null
    ) : BaseStatus

    data class Port(
        val ports: Int,
        val targetPort: IntOrString? = null,
        val protocol: Protocol? = null,
        val name: String? = null,
        val nodePort: Int? = null,
        val appProtocol: String? = null
    ) : BasePort {
        data class Status(
            val port: Int? = null,
            val protocol: Protocol? = null,
            val error: String? = null
        )
    }

    data class SessionAffinityConfig(
        val clientIP: ClientIPConfig? = null
    )

    data class ClientIPConfig(val timeoutSeconds: Int? = null)

    data class LoadBalancerStatus(
        val ingress: List<LoadBalancerIngress>? = null
    )

    data class LoadBalancerIngress(
        val hostname: String? = null,
        val ip: String? = null,
        val ipMode: String? = null,
        val ports: List<Port.Status>? = null
    ) : BaseLoadBalancerIngress
}