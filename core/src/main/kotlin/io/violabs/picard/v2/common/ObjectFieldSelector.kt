package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/object-field-selector/#ObjectFieldSelector
 *
 * import "k8s.io/api/core/v1"
 *
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
    val apiVersion: APIVersion? = null
)