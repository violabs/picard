package io.violabs.picard.v2.resources.authorization.review.access.subject.self

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.NonResourceAttributes
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.ResourceAttributes

/**
 * SelfSubjectAccessReviewSpec is a description of the access request.
 * Exactly one of ResourceAuthorizationAttributes and NonResourceAuthorizationAttributes must be set
 */
@GeneratedDsl
data class SelfSubjectAccessReviewSpec(
    /**
     * NonResourceAttributes describes information for a non-resource access request
     *
     * NonResourceAttributes includes the authorization attributes available for
     * non-resource requests to the Authorizer interface
     */
    val nonResourceAttributes: NonResourceAttributes? = null,
    /**
     * resourceAttributes (ResourceAttributes)
     *
     * ResourceAuthorizationAttributes describes information for a resource access request
     *
     * ResourceAttributes includes the authorization attributes available
     * for resource requests to the Authorizer interface
     */
    val resourceAttributes: ResourceAttributes? = null
)

