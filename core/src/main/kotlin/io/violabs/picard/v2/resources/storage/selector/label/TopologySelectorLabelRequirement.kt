package io.violabs.picard.v2.resources.storage.selector.label

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * A topology selector requirement is a selector that matches given label. This is an
 * alpha feature and may change in the future.
 */
@GeneratedDsl(withListGroup = true)
data class TopologySelectorLabelRequirement(
    /**
     * The label key that the selector applies to.
     */
    val key: String,
    /**
     * Atomic: will be replaced during a merge
     *
     * An array of string values. One value must match the label to be selected. Each entry in Values is ORed.
     */
    val values: List<String>
)