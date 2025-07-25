package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/local-object-reference/#LocalObjectReference
 * "k8s.io/api/core/v1"
 *
 * Contains enough information to let you locate the referenced object inside the same namespace.
 */
@GeneratedDsl(withListGroup = true)
data class LocalObjectReference(
    /**
     * Name of the referent. This field is effectively required, but due to backwards
     * compatibility is allowed to be empty. Instances of this type with an empty value
     * here are almost certainly wrong.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String
)