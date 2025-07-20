package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema
import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchemaList
import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRange
import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRangeList
import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicy
import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicyList
import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudget
import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudgetList
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfiguration
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationList
import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuota
import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuotaList
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicy
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicyList
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBinding
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBindingList

interface PolicyResource<T : APIVersion, META> : K8sResource<T, META>
interface PolicyListResource<T : APIVersion, E> : K8sListResource<T, E>

data class PolicyResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder : DslBuilder<PolicyResourceSection> {
        private val resources: MutableList<PolicyResource<*, *>> = mutableListOf()
        private val lists: MutableList<PolicyListResource<*, *>> = mutableListOf()

        fun flowSchema(init: FlowSchema.Builder.() -> Unit) {
            resources += FlowSchema.Builder().apply(init).build()
        }

        fun flowSchemaList(init: FlowSchemaList.Builder.() -> Unit) {
            lists += FlowSchemaList.Builder().apply(init).build()
        }

        fun limitRange(init: LimitRange.Builder.() -> Unit) {
            resources += LimitRange.Builder().apply(init).build()
        }

        fun limitRangeList(init: LimitRangeList.Builder.() -> Unit) {
            lists += LimitRangeList.Builder().apply(init).build()
        }

        fun networkPolicy(init: NetworkPolicy.Builder.() -> Unit) {
            resources += NetworkPolicy.Builder().apply(init).build()
        }

        fun networkPolicyList(init: NetworkPolicyList.Builder.() -> Unit) {
            lists += NetworkPolicyList.Builder().apply(init).build()
        }

        fun podDisruptionBudget(init: PodDisruptionBudget.Builder.() -> Unit) {
            resources += PodDisruptionBudget.Builder().apply(init).build()
        }

        fun podDisruptionBudgetList(init: PodDisruptionBudgetList.Builder.() -> Unit) {
            lists += PodDisruptionBudgetList.Builder().apply(init).build()
        }

        fun priorityLevelConfiguration(init: PriorityLevelConfiguration.Builder.() -> Unit) {
            resources += PriorityLevelConfiguration.Builder().apply(init).build()
        }

        fun priorityLevelConfigurationList(init: PriorityLevelConfigurationList.Builder.() -> Unit) {
            lists += PriorityLevelConfigurationList.Builder().apply(init).build()
        }

        fun resourceQuota(init: ResourceQuota.Builder.() -> Unit) {
            resources += ResourceQuota.Builder().apply(init).build()
        }

        fun resourceQuotaList(init: ResourceQuotaList.Builder.() -> Unit) {
            lists += ResourceQuotaList.Builder().apply(init).build()
        }

        fun validatingAdmissionPolicy(init: ValidatingAdmissionPolicy.Builder.() -> Unit) {
            resources += ValidatingAdmissionPolicy.Builder().apply(init).build()
        }

        fun validatingAdmissionPolicyList(init: ValidatingAdmissionPolicyList.Builder.() -> Unit) {
            lists += ValidatingAdmissionPolicyList.Builder().apply(init).build()
        }

        fun validatingAdmissionPolicyBinding(init: ValidatingAdmissionPolicyBinding.Builder.() -> Unit) {
            resources += ValidatingAdmissionPolicyBinding.Builder().apply(init).build()
        }

        fun validatingAdmissionPolicyBindingList(init: ValidatingAdmissionPolicyBindingList.Builder.() -> Unit) {
            lists += ValidatingAdmissionPolicyBindingList.Builder().apply(init).build()
        }

        override fun build(): PolicyResourceSection {
            return PolicyResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}