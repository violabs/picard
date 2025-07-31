package io.violabs.picard.v2.resources.service.ingress.http.backend

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * TypedLocalObjectReference contains enough information to let you locate the referenced object
 * inside the same namespace.
 */
@GeneratedDsl
data class TypedLocalObjectReference(
    /**
     * Kind is the type of resource being referenced
     */
    val kind: String,
    /**
     * Name is the name of resource being referenced
     */
    val name: String,
    /**
     * APIGroup is the group for the resource being referenced. If APIGroup is not specified, the
     * specified Kind must be in the core API group. For any other third-party types, APIGroup is required.
     */
    val apiGroup: String? = null
)