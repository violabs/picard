package io.violabs.picard.domain.k8sResources.policy.flowSchema

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.FlowDistinguisherMethod
import io.violabs.picard.domain.k8sResources.policy.PolicyRulesWithSubjects
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationReference

data class FlowSchema(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<FlowSchema.Version> {
    interface Version : APIVersion

    data class Spec(
        val priorityLevelConfiguration: PriorityLevelConfigurationReference,
        val distinguisherMethod: FlowDistinguisherMethod? = null,
        val matchingPrecedence: Int? = null,
        val rules: List<PolicyRulesWithSubjects>? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
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
        class Builder : DSLBuilder<Status> {
            private var conditions: List<Condition>? = null

            fun conditions(scope: ConditionGroup<Condition, Condition.Builder>.() -> Unit) {
                conditions = ConditionGroup(Condition.Builder()).apply(scope).conditions()
            }

            override fun build(): Status {
                return Status(
                    conditions = vRequireNotEmpty(this::conditions)
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
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
        fun schema(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}