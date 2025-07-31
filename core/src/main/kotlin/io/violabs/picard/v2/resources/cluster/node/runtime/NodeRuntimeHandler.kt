package io.violabs.picard.v2.resources.cluster.node.runtime

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeRuntimeHandler is a set of runtime handler information.
 */
@GeneratedDsl(withListGroup = true)
data class NodeRuntimeHandler(
    /**
     * Supported features.
     */
    val features: NodeRuntimeHandlerFeatures? = null,
    /**
     * Runtime handler name. Empty for the default runtime handler.
     */
    val name: String? = null
)