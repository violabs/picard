package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class PodFailurePolicyRule(
    val action: Action,
    val onExitCodes: OnExitCodesRequirement? = null,
    val onPodConditions: List<OnPodConditionsPattern>? = null
) {

    enum class Action {
        Count,
        FailIndex,
        FailJob,
        Ignore
    }

    class Builder : DslBuilder<PodFailurePolicyRule> {
        var action: Action? = null
        private var onExitCodes: OnExitCodesRequirement? = null
        private var onPodConditions: List<OnPodConditionsPattern>? = null

        fun onExitCodes(block: OnExitCodesRequirement.Builder.() -> Unit) {
            onExitCodes = OnExitCodesRequirement.Builder().apply(block).build()
        }

        fun onPodConditions(block: OnPodConditionsPattern.Group.() -> Unit) {
            onPodConditions = OnPodConditionsPattern.Group().apply(block).patterns()
        }

        override fun build(): PodFailurePolicyRule {
            return PodFailurePolicyRule(
                action = vRequireNotNull(this::action),
                onExitCodes = onExitCodes,
                onPodConditions = onPodConditions
            )
        }
    }

    class Group : BuilderGroup<PodFailurePolicyRule, Builder>(Builder()) {
        fun rules(): List<PodFailurePolicyRule>? = items()

        fun addPodFailurePolicyRule(block: Builder.() -> Unit) {
            add(block)
        }
    }
}