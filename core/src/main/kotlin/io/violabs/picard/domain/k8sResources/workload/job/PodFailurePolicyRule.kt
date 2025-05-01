package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class PodFailurePolicyRule(
    val action: Action,
    val onExitCods: OnExitCodesRequirement? = null,
    val onPodConditions: List<OnPodConditionsPattern>? = null
) {

    enum class Action {
        Count,
        FailIndex,
        FailJob,
        Ignore
    }

    class Builder : DSLBuilder<PodFailurePolicyRule> {
        var action: Action? = null
        private var onExitCods: OnExitCodesRequirement? = null
        private var onPodConditions: List<OnPodConditionsPattern>? = null

        fun onExitCodes(block: OnExitCodesRequirement.Builder.() -> Unit) {
            onExitCods = OnExitCodesRequirement.Builder().apply(block).build()
        }

        fun onPodConditions(block: OnPodConditionsPattern.Group.() -> Unit) {
            onPodConditions = OnPodConditionsPattern.Group().apply(block).patterns()
        }

        override fun build(): PodFailurePolicyRule {
            return PodFailurePolicyRule(
                action = requireNotNull(action),
                onExitCods = onExitCods,
                onPodConditions = onPodConditions
            )
        }
    }

    class Group : BuilderGroup<PodFailurePolicyRule, Builder>(Builder()) {
        fun rules(): List<PodFailurePolicyRule>? = items()

        fun rule(block: Builder.() -> Unit) {
            add(block)
        }
    }
}