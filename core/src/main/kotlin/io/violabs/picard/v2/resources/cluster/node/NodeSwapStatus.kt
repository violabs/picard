package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeSwapStatus represents swap memory information.
 */
@GeneratedDsl
data class NodeSwapStatus(
    /**
     * Total amount of swap memory in bytes.
     */
    val capacity: Long? = null
)