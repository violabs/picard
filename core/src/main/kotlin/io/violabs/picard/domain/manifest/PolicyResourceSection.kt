package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.policy.admission.mutating.MutatingAdmissionPolicyDslBuilder
import io.violabs.picard.v2.resources.policy.admission.mutating.MutatingAdmissionPolicyDslBuilderScope
import io.violabs.picard.v2.resources.policy.admission.mutating.binding.MutatingAdmissionPolicyBindingDslBuilder
import io.violabs.picard.v2.resources.policy.admission.mutating.binding.MutatingAdmissionPolicyBindingDslBuilderScope
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyDslBuilder
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyDslBuilderScope
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyListDslBuilder
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyListDslBuilderScope
import io.violabs.picard.v2.resources.policy.admission.validating.binding.ValidatingAdmissionPolicyBindingDslBuilder
import io.violabs.picard.v2.resources.policy.admission.validating.binding.ValidatingAdmissionPolicyBindingDslBuilderScope
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetDslBuilder
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetDslBuilderScope
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetListDslBuilder
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetListDslBuilderScope
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfigurationDslBuilder
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfigurationDslBuilderScope
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfigurationListDslBuilder
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfigurationListDslBuilderScope
import io.violabs.picard.v2.resources.policy.limit.LimitRangeDslBuilder
import io.violabs.picard.v2.resources.policy.limit.LimitRangeDslBuilderScope
import io.violabs.picard.v2.resources.policy.limit.LimitRangeListDslBuilder
import io.violabs.picard.v2.resources.policy.limit.LimitRangeListDslBuilderScope
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyDslBuilder
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyDslBuilderScope
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyListDslBuilder
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyListDslBuilderScope
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuotaDslBuilder
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuotaDslBuilderScope
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuotaListDslBuilder
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuotaListDslBuilderScope
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaDslBuilder
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaDslBuilderScope
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaListDslBuilder
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaListDslBuilderScope

interface PolicyResource<T : APIVersion, META> : K8sResource<T, META>
interface PolicyListResource<T : APIVersion, E> : K8sListResource<T, E>

data class PolicyResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<PolicyResource<*, *>> = mutableListOf(),
        private val lists: MutableList<PolicyListResource<*, *>> = mutableListOf()
    ) : DslBuilder<PolicyResourceSection> {

        fun flowSchema(block: FlowSchemaDslBuilderScope) {
            val flowSchema = FlowSchemaDslBuilder().apply(block).build()
            resources.add(flowSchema)
        }

        fun flowSchemaList(block: FlowSchemaListDslBuilderScope) {
            val flowSchemaList = FlowSchemaListDslBuilder().apply(block).build()
            lists.add(flowSchemaList)
        }

        fun limitRange(block: LimitRangeDslBuilderScope) {
            val limitRange = LimitRangeDslBuilder().apply(block).build()
            resources.add(limitRange)
        }

        fun limitRangeList(block: LimitRangeListDslBuilderScope) {
            val limitRangeList = LimitRangeListDslBuilder().apply(block).build()
            lists.add(limitRangeList)
        }

        fun networkPolicy(block: NetworkPolicyDslBuilderScope) {
            val networkPolicy = NetworkPolicyDslBuilder().apply(block).build()
            resources.add(networkPolicy)
        }

        fun networkPolicyList(block: NetworkPolicyListDslBuilderScope) {
            val networkPolicyList = NetworkPolicyListDslBuilder().apply(block).build()
            lists.add(networkPolicyList)
        }

        fun podDisruptionBudget(block: PodDisruptionBudgetDslBuilderScope) {
            val podDisruptionBudget = PodDisruptionBudgetDslBuilder().apply(block).build()
            resources.add(podDisruptionBudget)
        }

        fun podDisruptionBudgetList(block: PodDisruptionBudgetListDslBuilderScope) {
            val podDisruptionBudgetList = PodDisruptionBudgetListDslBuilder().apply(block).build()
            lists.add(podDisruptionBudgetList)
        }

        fun priorityLevelConfiguration(block: PriorityLevelConfigurationDslBuilderScope) {
            val priorityLevelConfiguration = PriorityLevelConfigurationDslBuilder().apply(block).build()
            resources.add(priorityLevelConfiguration)
        }

        fun priorityLevelConfigurationList(block: PriorityLevelConfigurationListDslBuilderScope) {
            val priorityLevelConfigurationList = PriorityLevelConfigurationListDslBuilder().apply(block).build()
            lists.add(priorityLevelConfigurationList)
        }

        fun resourceQuota(block: ResourceQuotaDslBuilderScope) {
            val resourceQuota = ResourceQuotaDslBuilder().apply(block).build()
            resources.add(resourceQuota)
        }

        fun resourceQuotaList(block: ResourceQuotaListDslBuilderScope) {
            val resourceQuotaList = ResourceQuotaListDslBuilder().apply(block).build()
            lists.add(resourceQuotaList)
        }

        fun validatingAdmissionPolicy(block: ValidatingAdmissionPolicyDslBuilderScope) {
            val validatingAdmissionPolicy = ValidatingAdmissionPolicyDslBuilder().apply(block).build()
            resources.add(validatingAdmissionPolicy)
        }

        fun validatingAdmissionPolicyList(block: ValidatingAdmissionPolicyListDslBuilderScope) {
            val validatingAdmissionPolicyList = ValidatingAdmissionPolicyListDslBuilder().apply(block).build()
            lists.add(validatingAdmissionPolicyList)
        }

        fun validatingAdmissionPolicyBinding(block: ValidatingAdmissionPolicyBindingDslBuilderScope) {
            val validatingAdmissionPolicyBinding = ValidatingAdmissionPolicyBindingDslBuilder().apply(block).build()
            resources.add(validatingAdmissionPolicyBinding)
        }

        fun mutatingAdmissionPolicy(block: MutatingAdmissionPolicyDslBuilderScope) {
            val mutatingAdmissionPolicy = MutatingAdmissionPolicyDslBuilder().apply(block).build()
            resources.add(mutatingAdmissionPolicy)
        }

        fun mutatingAdmissionPolicyBinding(block: MutatingAdmissionPolicyBindingDslBuilderScope) {
            val mutatingAdmissionPolicyBinding = MutatingAdmissionPolicyBindingDslBuilder().apply(block).build()
            resources.add(mutatingAdmissionPolicyBinding)
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