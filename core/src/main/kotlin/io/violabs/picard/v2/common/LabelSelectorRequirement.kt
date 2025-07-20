package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * A selector that contains values, a key, and an operator that relates the key and values.
 */
@GeneratedDsl(withListGroup = true)
data class LabelSelectorRequirement(
    /**
     * matchExpressions.key (string), required
     */
    val key: String,
    /**
     * key is the label key that the selector applies to.
     *
     * matchExpressions.operator (string), required
     */
    val operator: String,
    /**
     * operator represents a key's relationship to a set of values. Valid operators are In, NotIn, Exists and DoesNotExist.
     *
     * matchExpressions.values ([]string)
     *
     * Atomic: will be replaced during a merge
     *
     * values is an array of string values. If the operator is In or NotIn, the values array must be non-empty. If the operator is Exists or DoesNotExist, the values array must be empty. This array is replaced during a strategic merge patch.
     */
    val values: List<String>? = null
)