package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.manifest.PolicyResource

data class NetworkPolicy(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : PolicyResource<NetworkPolicy.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val podSelector: LabelSelector,
        val policyTypes: List<String>? = null,
        val ingress: List<NetworkPolicyIngressRule>? = null,
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
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

    class Builder : ResourceSpecDslBuilder<NetworkPolicy, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): NetworkPolicy {
            return NetworkPolicy(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<NetworkPolicy, Builder>(Builder()) {
        fun networkPolicyItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}