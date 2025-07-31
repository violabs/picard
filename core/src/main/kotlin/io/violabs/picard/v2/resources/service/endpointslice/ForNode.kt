package io.violabs.picard.v2.resources.service.endpointslice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ForNode provides information about which nodes should consume this endpoint.
 */
@GeneratedDsl(withListGroup = true)
data class ForNode(
    /**
     * name represents the name of the node.
     */
    val name: String
)