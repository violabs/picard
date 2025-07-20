package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.policy.LimitResponse

data class LimitedPriorityLevelConfiguration(
    val borrowingLimitPercent: Int? = null,
    val lendablePercent: Int? = null,
    val limitResponse: LimitResponse? = null,
    val nominalConcurrencyShares: Int? = null,
) {
    class Builder : DslBuilder<LimitedPriorityLevelConfiguration> {
        var borrowingLimitPercent: Int? = null
        var lendablePercent: Int? = null
        private var limitResponse: LimitResponse? = null
        var nominalConcurrencyShares: Int? = null

        fun limitResponse(scope: LimitResponse.Builder.() -> Unit) {
            this.limitResponse = LimitResponse.Builder().apply(scope).build()
        }

        override fun build(): LimitedPriorityLevelConfiguration {
            return LimitedPriorityLevelConfiguration(
                borrowingLimitPercent = borrowingLimitPercent,
                lendablePercent = lendablePercent,
                limitResponse = limitResponse,
                nominalConcurrencyShares = nominalConcurrencyShares
            )
        }
    }
}