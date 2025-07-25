package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.NodeSelectorRequirement

@GeneratedDsl(withListGroup = true)
data class NodeSelectorTerm(
    /**
     * nodeAffinity.required.nodeSelectorTerms.matchExpressions ([]NodeSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * A list of node selector requirements by node's labels.
     */
    val matchExpressions: List<NodeSelectorRequirement>? = null,
    /**
     * nodeAffinity.required.nodeSelectorTerms.matchFields ([]NodeSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * A list of node selector requirements by node's fields.
     */
    val matchFields: List<NodeSelectorRequirement>? = null
)