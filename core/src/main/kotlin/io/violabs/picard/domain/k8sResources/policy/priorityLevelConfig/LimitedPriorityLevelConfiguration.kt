package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.domain.k8sResources.policy.LimitResponse

data class LimitedPriorityLevelConfiguration(
    val borrowingLimitPercent: Int? = null,
    val lendablePercent: Int? = null,
    val limitResponse: LimitResponse? = null,
    val nominalConcurrencyShares: Int? = null,
)