package io.violabs.picard.v2.resources.cluster.node.runtime

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeRuntimeHandlerFeatures is a set of features implemented by the runtime handler.
 */
@GeneratedDsl
data class NodeRuntimeHandlerFeatures(
    /**
     * RecursiveReadOnlyMounts is set to true if the runtime handler supports RecursiveReadOnlyMounts.
     */
    val recursiveReadOnlyMounts: Boolean? = null,
    /**
     * UserNamespaces is set to true if the runtime handler supports UserNamespaces, including for volumes.
     */
    val userNamespaces: Boolean? = null
)