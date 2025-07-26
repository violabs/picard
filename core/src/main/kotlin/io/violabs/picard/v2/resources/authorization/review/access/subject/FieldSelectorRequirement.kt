package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * FieldSelectorRequirement is a selector that contains values,
 * a key, and an operator that relates the key and values.
 */
@GeneratedDsl(withListGroup = true)
data class FieldSelectorRequirement(
    /**
     * key is the field selector key that the requirement applies to.
     */
    val key: String,
    /**
     * operator represents a key's relationship to a set of values.
     * Valid operators are In, NotIn, Exists, DoesNotExist. The list of
     * operators may grow in the future.
     */
    val operator: Operator,
    /**
     * Atomic: will be replaced during a merge
     *
     * values is an array of string values. If the operator is In or NotIn,
     * the values array must be non-empty. If the operator is Exists or
     * DoesNotExist, the values array must be empty.
     */
    val values: List<String>? = null
) {
    enum class Operator {
        In,
        NotIn,
        Exists,
        DoesNotExist
    }
}

