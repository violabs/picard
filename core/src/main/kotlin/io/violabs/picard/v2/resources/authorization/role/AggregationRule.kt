package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

/**
 * AggregationRule describes how to locate ClusterRoles to aggregate
 * into the ClusterRole
 */
@GeneratedDsl
data class AggregationRule(
    /**
     * Atomic: will be replaced during a merge
     *
     * ClusterRoleSelectors holds a list of selectors which will be used to find
     * ClusterRoles and create the rules. If any of the selectors match, then the
     * ClusterRole's permissions will be added
     */
    val clusterRoleSelectors: List<LabelSelector>? = null
)

