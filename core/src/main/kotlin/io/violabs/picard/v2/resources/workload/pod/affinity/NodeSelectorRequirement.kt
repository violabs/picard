package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeSelectorRequirement is a selector that contains values, a key, and an operator
 * that relates the key and values.
 */
@GeneratedDsl(withListGroup = true)
data class NodeSelectorRequirement(
    /**
     * The label key that the selector applies to.
     */
    val key: String,
    /**
     * Represents a key's relationship to a set of values.
     * Valid operators are In, NotIn, Exists, DoesNotExist. Gt, and Lt.
     */
    val operator: String,
    /**
     * An array of string values. If the operator is In or NotIn,
     * the values array must be non-empty. If the operator is Exists or DoesNotExist,
     * the values array must be empty. If the operator is Gt or Lt, the values
     * array must have a single element, which will be interpreted as an integer.
     * This array is replaced during a strategic merge patch.
     */
    val values: List<String>? = null
)