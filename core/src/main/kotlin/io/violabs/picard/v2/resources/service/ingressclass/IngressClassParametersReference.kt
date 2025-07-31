package io.violabs.picard.v2.resources.service.ingressclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * IngressClassParametersReference identifies an API object. This can be used to specify 
 * a cluster or namespace-scoped resource.
 */
@GeneratedDsl
data class IngressClassParametersReference(
    /**
     * kind is the type of resource being referenced.
     */
    val kind: String,
    /**
     * name is the name of resource being referenced.
     */
    val name: String,
    /**
     * apiGroup is the group for the resource being referenced. If APIGroup is not specified, 
     * the specified Kind must be in the core API group. For any other third-party types, 
     * APIGroup is required.
     */
    val apiGroup: String? = null,
    /**
     * namespace is the namespace of the resource being referenced. This field is required 
     * when scope is set to "Namespace" and must be unset when scope is set to "Cluster".
     */
    val namespace: String? = null,
    /**
     * scope represents if this refers to a cluster or namespace scoped resource. 
     * This may be set to "Cluster" (default) or "Namespace".
     */
    val scope: String? = null
)