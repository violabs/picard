package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ParentReference describes a reference to a parent object.
 */
@GeneratedDsl
data class ParentReference(
    /**
     * Name is the name of the object being referenced.
     */
    val name: String,
    /**
     * Resource is the resource of the object being referenced.
     */
    val resource: String,
    /**
     * Group is the group of the object being referenced.
     */
    val group: String? = null,
    /**
     * Namespace is the namespace of the object being referenced.
     */
    val namespace: String? = null
)