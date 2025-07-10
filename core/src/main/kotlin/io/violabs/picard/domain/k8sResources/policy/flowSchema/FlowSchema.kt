package io.violabs.picard.domain.k8sResources.policy.flowSchema

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.FlowDistinguisherMethod
import io.violabs.picard.domain.k8sResources.policy.PolicyRulesWithSubjects
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationReference
import io.violabs.picard.domain.manifest.PolicyResource

data class FlowSchema(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : PolicyResource<FlowSchema.Version> {
    interface Version : APIVersion

    data class Spec(
        val priorityLevelConfiguration: PriorityLevelConfigurationReference,
        val distinguisherMethod: FlowDistinguisherMethod? = null,
        val matchingPrecedence: Int? = null,
        val rules: List<PolicyRulesWithSubjects>? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var priorityLevelConfiguration: PriorityLevelConfigurationReference? = null
            private var distinguisherMethod: FlowDistinguisherMethod? = null
            var matchingPrecedence: Int? = null
            private var rules: List<PolicyRulesWithSubjects>? = null

            fun priorityLevelConfiguration(ref: String) {
                priorityLevelConfiguration = PriorityLevelConfigurationReference(ref)
            }

            fun distinguisherMethod(type: FlowDistinguisherMethod.Type) {
                distinguisherMethod = FlowDistinguisherMethod(type.name)
            }

            fun rules(scope: PolicyRulesWithSubjects.Group.() -> Unit) {
                rules = PolicyRulesWithSubjects.Group().apply(scope).ruleGroups()
            }

            override fun build(): Spec {
                return Spec(
                    priorityLevelConfiguration = vRequireNotNull(this::priorityLevelConfiguration),
                    distinguisherMethod = distinguisherMethod,
                    matchingPrecedence = matchingPrecedence,
                    rules = rules
                )
            }
        }
    }

    data class Status(
        val conditions: List<Condition>
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var conditions: List<Condition>? = null

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            override fun build(): Status {
                return Status(
                    conditions = vRequireNotEmpty(this::conditions)
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        FlowSchema,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): FlowSchema {
            return FlowSchema(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<FlowSchema, Builder>(Builder()) {
        fun flowSchemaItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}