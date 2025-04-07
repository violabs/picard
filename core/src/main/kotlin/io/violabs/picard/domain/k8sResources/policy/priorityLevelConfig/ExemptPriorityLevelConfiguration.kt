package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

data class ExemptPriorityLevelConfiguration(
    val lendablePercent: Int? = null,
    val nominalConcurrencyShares: Int? = null,

)