package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class NetworkPolicy(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<NetworkPolicy.Version> {
    interface Version : APIVersion

    data class Spec(
        val podSelector: LabelSelector,
        val policyTypes: List<String>? = null,
        val ingress: List<NetworkPolicyIngressRule>? = null,
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var podSelector: LabelSelector? = null
            private var policyTypes: List<String>? = null
            private var ingress: List<NetworkPolicyIngressRule>? = null

            fun podSelector(block: LabelSelector.Builder.() -> Unit) {
                this.podSelector = LabelSelector.Builder().apply(block).build()
            }

            fun policyTypes(policyTypes: List<String>) {
                this.policyTypes = policyTypes
            }

            fun ingress(block: NetworkPolicyIngressRule.Group.() -> Unit) {
                this.ingress = NetworkPolicyIngressRule.Group().apply(block).rules()
            }

            override fun build(): Spec {
                return Spec(
                    podSelector = vRequireNotNull(this::podSelector),
                    policyTypes = policyTypes,
                    ingress = ingress
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<NetworkPolicy, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): NetworkPolicy {
            return NetworkPolicy(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<NetworkPolicy, Builder>(Builder()) {
        fun policy(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}