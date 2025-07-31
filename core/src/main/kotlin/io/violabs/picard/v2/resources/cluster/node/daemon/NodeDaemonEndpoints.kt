package io.violabs.picard.v2.resources.cluster.node.daemon

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.cluster.node.daemon.DaemonEndpoint

/**
 * NodeDaemonEndpoints lists ports opened by daemons running on the Node.
 */
@GeneratedDsl
data class NodeDaemonEndpoints(
    /**
     * Endpoint on which Kubelet is listening.
     */
    val kubeletEndpoint: DaemonEndpoint? = null
)