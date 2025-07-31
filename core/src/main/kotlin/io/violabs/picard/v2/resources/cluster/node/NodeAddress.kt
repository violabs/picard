package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeAddress contains information for the node's address.
 */
@GeneratedDsl(withListGroup = true)
data class NodeAddress(
    /**
     * The node address.
     */
    val address: String,
    /**
     * Node address type, one of Hostname, ExternalIP or InternalIP.
     */
    val type: String
)