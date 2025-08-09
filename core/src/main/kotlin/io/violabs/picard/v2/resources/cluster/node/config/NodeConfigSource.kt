package io.violabs.picard.v2.resources.cluster.node.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.cluster.node.config.ConfigMapNodeConfigSource

/**
 * NodeConfigSource specifies a source of node configuration.
 * Exactly one subfield (excluding metadata) must be non-nil.
 * This API is deprecated since 1.22
 */
@GeneratedDsl
data class NodeConfigSource(
    /**
     * ConfigMap is a reference to a Node's ConfigMap
     */
    val configMap: ConfigMapNodeConfigSource? = null
)