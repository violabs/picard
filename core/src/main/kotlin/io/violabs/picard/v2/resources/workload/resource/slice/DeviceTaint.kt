package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * The device this taint is attached to has the "effect" on any claim which does not 
 * tolerate the taint and, through the claim, to pods using the claim.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceTaint(
    /**
     * The effect of the taint on claims that do not tolerate the taint and through such 
     * claims on the pods using them. Valid effects are NoSchedule and NoExecute. 
     * PreferNoSchedule as used for nodes is not valid here.
     */
    val effect: Effect,
    /**
     * The taint key to be applied to a device. Must be a label name.
     */
    val key: String,
    /**
     * TimeAdded represents the time at which the taint was added. 
     * Added automatically during create or update if not set.
     * 
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. 
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val timeAdded: LocalDateTime? = null,
    /**
     * The taint value corresponding to the taint key. Must be a label value.
     */
    val value: String? = null
) {
    enum class Effect {
        NoSchedule,
        NoExecute
    }
}