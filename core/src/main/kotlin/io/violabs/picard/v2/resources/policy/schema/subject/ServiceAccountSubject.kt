package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ServiceAccountSubject holds detailed information for service-account-kind subject.
 */
@GeneratedDsl
data class ServiceAccountSubject(
    /**
     * name is the name of matching ServiceAccount objects, or "*" to match
     * regardless of name. Required.
     */
    val name: String,
    /**
     * namespace is the namespace of matching ServiceAccount objects. Required.
     */
    val namespace: String
)
