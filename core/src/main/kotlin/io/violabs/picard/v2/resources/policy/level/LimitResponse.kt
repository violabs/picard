package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * LimitResponse defines how to handle requests that can not be executed right now.
 */
@GeneratedDsl
data class LimitResponse(
    /**
     * type is "Queue" or "Reject". 
     * "Queue" means that requests that can not be executed upon arrival are held in a queue until they can be executed or a queuing limit is reached.
     * "Reject" means that requests that can not be executed upon arrival are rejected. 
     * Required.
     */
    val type: Type,
    /**
     * queuing holds the configuration parameters for queuing.
     * This field may be non-empty only if type is "Queue".
     */
    val queuing: QueuingConfiguration? = null
) {
    enum class Type {
        Queue,
        Reject
    }
}