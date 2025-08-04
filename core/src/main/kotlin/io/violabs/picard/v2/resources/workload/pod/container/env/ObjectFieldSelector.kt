package io.violabs.picard.v2.resources.workload.pod.container.env

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ObjectFieldSelector selects an APIVersioned field of an object.
 */
@GeneratedDsl
data class ObjectFieldSelector(
    /**
     * Path of the field to select in the specified API version.
     */
    val fieldPath: String,
    /**
     * Version of the schema the FieldPath is written in terms of, defaults to "v1".
     */
    val apiVersion: String? = null
)