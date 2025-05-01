package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class PriorityLevelConfiguration(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<PriorityLevelConfiguration.Version> {
    interface Version : APIVersion

    data class Spec(
        val type: Type,
        val exempt: ExemptPriorityLevelConfiguration? = null,
        val limited: LimitedPriorityLevelConfiguration? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
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
        class Builder : DSLBuilder<Status> {
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

    class Builder : ResourceSpecStatusDSLBuilder<
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
        fun config(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}