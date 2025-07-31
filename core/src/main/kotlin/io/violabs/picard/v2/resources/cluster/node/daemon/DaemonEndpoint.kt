package io.violabs.picard.v2.resources.cluster.node.daemon

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DaemonEndpoint contains information about a single Daemon endpoint.
 */
@GeneratedDsl
data class DaemonEndpoint(
    /**
     * Port number of the given endpoint.
     */
    @JsonProperty("Port")
    val port: Int
)