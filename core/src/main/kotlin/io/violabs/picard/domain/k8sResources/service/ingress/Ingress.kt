package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.BaseLoadBalancerIngress
import io.violabs.picard.domain.BasePort
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus


data class Ingress(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Ingress.Version> {
    interface Version : APIVersion

    data class Spec(
        val defaultBackend: Backend? = null,
        val ingressClassName: String? = null,
        val rules: List<Rule>? = null,
        val tls: List<TLS>? = null
    ) : BaseSpec

    data class Status(
        val loadBalancer: LoadBalancerIngress? = null
    ) : BaseStatus

    data class Backend(
        val resource: TypedLocalObjectReference? = null,
        val service: ServiceBackend? = null
    )

    data class ServiceBackend(
        val name: String,
        val port: Port? = null
    ) {
        data class Port(
            val name: String? = null,
            val number: Int? = null
        ) : BasePort
    }

    data class Rule(
        val host: String? = null,
        val http: HTTPIngressRuleValue? = null
    )

    data class HTTPIngressRuleValue(
        val paths: List<HTTPIngressPath>
    )

    data class HTTPIngressPath(
        val backend: Backend,
        val pathType: String,
        val path: String? = null
    )

    data class TLS(
        val hosts: List<String>? = null,
        val secretName: String? = null
    )

    data class LoadBalancerIngress(
        val hostname: String? = null,
        val ip: String? = null,
        val ports: List<PortStatus>? = null
    ) : BaseLoadBalancerIngress

    data class PortStatus(
        val port: Int? = null,
        val protocol: Protocol? = null,
        val error: String? = null
    )
}

data class TypedLocalObjectReference(
    val kind: String,
    val name: String,
    val apiGroup: String? = null
)
