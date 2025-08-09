package io.violabs.picard.v2.resources.workload.resource.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceClaimConsumerReference contains enough information to let you locate the consumer 
 * of a ResourceClaim. The user must be a resource in the same namespace as the ResourceClaim.
 */
@GeneratedDsl(withListGroup = true)
data class ResourceClaimConsumerReference(
    /**
     * Name is the name of resource being referenced.
     */
    val name: String,
    /**
     * Resource is the type of resource being referenced, for example "pods".
     */
    val resource: String,
    /**
     * UID identifies exactly one incarnation of the resource.
     */
    val uid: String,
    /**
     * APIGroup is the group for the resource being referenced. It is empty for the core API. 
     * This matches the group in the APIVersion that is used when creating the resources.
     */
    val apiGroup: String? = null
)