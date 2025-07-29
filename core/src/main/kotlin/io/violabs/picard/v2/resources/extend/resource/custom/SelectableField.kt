package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SelectableField specifies the JSON path of a field that may be used with field selectors.
 */
@GeneratedDsl(withListGroup = true)
data class SelectableField(
    /**
     * jsonPath is a simple JSON path which is evaluated against each custom resource to produce a field selector value.
     * Only JSON paths without the array notation are allowed. Must point to a field of type string, boolean or integer.
     * Types with enum values and strings with formats are allowed. If jsonPath refers to absent field in a resource,
     * the jsonPath evaluates to an empty string. Must not point to metdata fields. Required.
     */
    val jsonPath: String? = null
)