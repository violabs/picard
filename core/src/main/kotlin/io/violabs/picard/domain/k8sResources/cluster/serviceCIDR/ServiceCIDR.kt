package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ServiceCIDR(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ServiceCIDR.Version> {
    interface Version : APIVersion

    data class Spec(val cidrs: List<String>? = null) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var cidrs: List<String>? = null

            fun cidrs(vararg cidrs: String) {
                this.cidrs = cidrs.toList()
            }

            override fun build(): Spec {
                return Spec(cidrs)
            }
        }
    }

    data class Status(
        val conditions: List<ServiceCondition>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var conditions: List<ServiceCondition>? = null

            fun conditions(scope: ConditionGroup<ServiceCondition, ServiceCondition.Builder>.() -> Unit) {
                conditions = ConditionGroup(ServiceCondition.Builder()).apply(scope).conditions()
            }

            override fun build(): Status {
                return Status(conditions)
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ServiceCIDR,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): ServiceCIDR {
            return ServiceCIDR(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ServiceCIDR, Builder>(Builder()) {
        fun serviceCIDR(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}