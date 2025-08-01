package io.violabs.picard.v2.resources.workload.resource.claim.device

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * The ResourceClaim this DeviceToleration is attached to tolerates any taint that matches 
 * the triple <key,value,effect> using the matching operator.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceToleration(
    /**
     * Effect indicates the taint effect to match. Empty means match all taint effects. 
     * When specified, allowed values are NoSchedule and NoExecute.
     */
    val effect: Effect? = null,
    /**
     * Key is the taint key that the toleration applies to. Empty means match all taint keys. 
     * If the key is empty, operator must be Exists; this combination means to match all values and all keys. 
     * Must be a label name.
     */
    val key: String? = null,
    /**
     * Operator represents a key's relationship to the value. Valid operators are Exists and Equal. 
     * Defaults to Equal. Exists is equivalent to wildcard for value, so that a ResourceClaim can 
     * tolerate all taints of a particular category.
     */
    val operator: Operator? = null,
    /**
     * TolerationSeconds represents the period of time the toleration (which must be of effect NoExecute, 
     * otherwise this field is ignored) tolerates the taint. By default, it is not set, which means 
     * tolerate the taint forever (do not evict). Zero and negative values will be treated as 0 
     * (evict immediately) by the system. If larger than zero, the time when the pod needs to be evicted 
     * is calculated as <time when taint was added> + <toleration seconds>.
     */
    val tolerationSeconds: Long? = null,
    /**
     * Value is the taint value the toleration matches to. If the operator is Exists, the value must be empty, 
     * otherwise just a regular string. Must be a label value.
     */
    val value: String? = null
) {
    enum class Operator {
        /**
         * Equal means that the toleration matches a taint with the same key and value.
         */
        Equal,

        /**
         * Exists means that the toleration matches a taint with the same key, regardless of value.
         */
        Exists
    }

    enum class Effect {
        /**
         * NoSchedule means that the pod will not be scheduled onto a node with this taint.
         */
        NoSchedule,

        /**
         * NoExecute means that the pod will be evicted from a node with this taint.
         */
        NoExecute
    }
}