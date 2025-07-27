package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * RoleRef contains information that points to the role being used
 */
@GeneratedDsl
data class RoleRef(
    /**
     * APIGroup is the group for the resource being referenced
     */
    val apiGroup: String,
    /**
     * Kind is the type of resource being referenced
     */
    val kind: String,
    /**
     * Name is the name of resource being referenced
     */
    val name: String
)

