package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * The pod this Toleration is attached to tolerates any taint that matches
 * the triple <key,value,effect> using the matching operator.
 */
@GeneratedDsl(withListGroup = true)
data class Toleration(
    /**
     * Key is the taint key that the toleration applies to. Empty means match all taint keys.
     * If the key is empty, operator must be Exists; this combination means to match all values and all keys.
     */
    val key: String? = null,
    /**
     * Operator represents a key's relationship to the value. Valid operators are Exists and Equal.
     * Defaults to Equal. Exists is equivalent to wildcard for value, so that a pod can tolerate all
     * taints of a particular category.
     */
    val operator: Operator? = null,
    /**
     * Value is the taint value the toleration matches to. If the operator is Exists, the value should
     * be empty, otherwise just a regular string.
     */
    val value: String? = null,
    /**
     * Effect indicates the taint effect to match. Empty means match all taint effects.
     * When specified, allowed values are NoSchedule, PreferNoSchedule and NoExecute.
     */
    val effect: String? = null,
    /**
     * TolerationSeconds represents the period of time the toleration (which must be of effect
     * NoExecute, otherwise this field is ignored) tolerates the taint.
     * By default, it is not set, which means tolerate the taint forever (do not evict).
     * Zero and negative values will be treated as 0 (evict immediately) by the system.
     */
    val tolerationSeconds: Long? = null
) {
    enum class Operator {
        /**
         * Exists is a wildcard operator that matches all values and all keys.
         */
        EXISTS,

        /**
         * Equal is an operator that matches a specific key and value.
         */
        EQUAL
    }
}