package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * The node this Taint is attached to has the "effect" on any pod that does not tolerate the Taint.
 */
@GeneratedDsl(withListGroup = true)
data class Taint(
    /**
     * Required. The effect of the taint on pods that do not tolerate the taint.
     * Valid effects are NoSchedule, PreferNoSchedule and NoExecute.
     */
    val effect: String,
    /**
     * Required. The taint key to be applied to a node.
     */
    val key: String,
    /**
     * TimeAdded represents the time at which the taint was added. It is only written for NoExecute taints.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to
     * YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.
     */
    val timeAdded: LocalDateTime? = null,
    /**
     * The taint value corresponding to the taint key.
     */
    val value: String? = null
)