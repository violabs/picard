package io.violabs.picard.v2.resources.workload.resource

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.NodeSelectorRequirement

/**
 * A node selector represents the union of the results of one or more label
 * queries over a set of nodes; that is, it represents the OR of the selectors
 * represented by the node selector terms.
 */
@GeneratedDsl(withListGroup = true)
data class NodeSelectorTerm(
    /**
     * devices.nodeSelector.nodeSelectorTerms.matchExpressions ([]NodeSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * A list of node selector requirements by node's labels.
     */
    val matchExpressions: List<NodeSelectorRequirement>? = null,
    /**
     * devices.nodeSelector.nodeSelectorTerms.matchFields ([]NodeSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * A list of node selector requirements by node's fields.
     */
    val matchFields: List<NodeSelectorRequirement>? = null
)