package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeSelector represents the union of the results of one or more label queries
 * over a set of nodes; that is, it represents the OR of the selectors represented
 * by the node selector terms.
 */
@GeneratedDsl
data class NodeSelector(
    /**
     * A list of node selector terms. The terms are ORed.
     */
    val nodeSelectorTerms: List<NodeSelectorTerm>
)