package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * A node selector represents the union of the results of one or more
 * label queries over a set of nodes; that is, it represents the OR
 * of the selectors represented by the node selector terms.
 */
@GeneratedDsl
data class NodeSelector(
    /**
     * Atomic: will be replaced during a merge
     *
     * Required. A list of node selector terms. The terms are ORed.
     *
     * A null or empty node selector term matches no objects. The requirements
     * of them are ANDed. The TopologySelectorTerm type implements a subset of
     * the NodeSelectorTerm.
     */
    val nodeSelectorTerms: List<NodeSelectorTerm>
)