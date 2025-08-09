package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.NodeSelectorRequirement

/**
 * A null or empty node selector term matches no objects. The requirements of
 * them are ANDed.
 * The TopologySelectorTerm type implements a subset of the NodeSelectorTerm.
 */
@GeneratedDsl(withListGroup = true)
data class NodeSelectorTerm(
    /**
     * A list of node selector requirements by node's labels.
     */
    val matchExpressions: List<NodeSelectorRequirement>? = null,
    /**
     * A list of node selector requirements by node's fields.
     */
    val matchFields: List<NodeSelectorRequirement>? = null
)