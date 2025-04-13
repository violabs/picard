package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.Operator

data class PodFailurePolicy(val rules: List<Rule>) {
    data class Rule(
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
    }

    data class OnExitCodesRequirement(
        val operator: Operator,
        val values: List<Int>,
        val containerName: String? = null
    )

    data class OnPodConditionsPattern(
        val status: BooleanType,
        val type: String
    )
}