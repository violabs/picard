package io.violabs.picard.v2.resources.workload.pod.container.resource

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceClaim references one entry in PodSpec.ResourceClaims.
 */
@GeneratedDsl(withListGroup = true)
data class ResourceClaim(
    /**
     * Name must match the name of one entry in pod.spec.resourceClaims of
     * the Pod where this field is used. It makes that resource available
     * inside a container.
     */
    val name: String,
    /**
     * Request is the name chosen for a request in the referenced claim.
     * If empty, everything from the claim is made available, otherwise
     * only the result of this request.
     */
    val request: String? = null
)