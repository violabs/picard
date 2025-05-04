package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.common.DSLBuilder

data class ExemptPriorityLevelConfiguration(
    val lendablePercent: Int? = null,
    val nominalConcurrencyShares: Int? = null,
) {
    class Builder : DSLBuilder<ExemptPriorityLevelConfiguration> {
        var lendablePercent: Int? = null
        var nominalConcurrencyShares: Int? = null

        override fun build(): ExemptPriorityLevelConfiguration {
            return ExemptPriorityLevelConfiguration(
                lendablePercent = lendablePercent,
                nominalConcurrencyShares = nominalConcurrencyShares
            )
        }
    }
}