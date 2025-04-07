package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.LabelSelector

data class AggregationRule(
    val clusterRoleSelectors: List<LabelSelector>? = null
)
