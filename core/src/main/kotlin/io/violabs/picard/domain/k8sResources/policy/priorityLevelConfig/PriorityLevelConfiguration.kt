package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource

data class PriorityLevelConfiguration(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : PolicyResource<PriorityLevelConfiguration.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val type: Type,
        val exempt: ExemptPriorityLevelConfiguration? = null,
        val limited: LimitedPriorityLevelConfiguration? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var type: Type? = null
            private var exempt: ExemptPriorityLevelConfiguration? = null
            private var limited: LimitedPriorityLevelConfiguration? = null

            fun exempt(scope: ExemptPriorityLevelConfiguration.Builder.() -> Unit) {
                this.exempt = ExemptPriorityLevelConfiguration.Builder().apply(scope).build()
            }

            fun limited(scope: LimitedPriorityLevelConfiguration.Builder.() -> Unit) {
                this.limited = LimitedPriorityLevelConfiguration.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    type = vRequireNotNull(this::type),
                    exempt = exempt,
                    limited = limited
                )
            }
        }
    }

    data class Status(
        val conditions: List<Condition>? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var conditions: List<Condition>? = null

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                this.conditions = Condition.group(scope)
            }

            override fun build(): Status {
                return Status(
                    conditions = conditions
                )
            }
        }
    }

    enum class Type {
        Exempt,
        Limited
    }

    class Builder : ResourceSpecStatusDslBuilder<
        PriorityLevelConfiguration,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {

        override fun build(): PriorityLevelConfiguration {
            return PriorityLevelConfiguration(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<PriorityLevelConfiguration, Builder>(Builder()) {
        fun priorityLevelConfigurationItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}