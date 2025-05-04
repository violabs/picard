package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.k8sResources.K8sListResource


data class Ingress(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<Ingress.Version> {
    interface Version : APIVersion

    data class Spec(
        val defaultBackend: IngressBackend? = null,
        val ingressClassName: String? = null,
        val rules: List<IngressRule>? = null,
        val tls: List<IngressTLS>? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var defaultBackend: IngressBackend? = null
            var ingressClassName: String? = null
            private var rules: List<IngressRule>? = null
            private var tls: List<IngressTLS>? = null

            fun defaultBackend(scope: IngressBackend.Builder.() -> Unit) {
                defaultBackend = IngressBackend.Builder().apply(scope).build()
            }

            fun rules(block: IngressRule.Group.() -> Unit) {
                rules = IngressRule.Group().apply(block).rules()
            }

            fun tlsList(block: IngressTLS.Group.() -> Unit) {
                tls = IngressTLS.Group().apply(block).tlsList()
            }

            override fun build(): Spec {
                return Spec(
                    defaultBackend = defaultBackend,
                    ingressClassName = ingressClassName,
                    rules = rules,
                    tls = tls
                )
            }
        }
    }

    data class Status(
        val loadBalancer: IngressLoadBalancerIngress? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var loadBalancer: IngressLoadBalancerIngress? = null

            fun loadBalancer(scope: IngressLoadBalancerIngress.Builder.() -> Unit) {
                loadBalancer = IngressLoadBalancerIngress.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    loadBalancer = loadBalancer
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        Ingress,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): Ingress {
            return Ingress(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Ingress, Builder>(Builder()) {
        fun ingress(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}

