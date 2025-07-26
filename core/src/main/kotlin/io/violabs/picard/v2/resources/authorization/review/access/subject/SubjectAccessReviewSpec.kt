package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SubjectAccessReviewSpec is a description of the access request.
 * Exactly one of ResourceAuthorizationAttributes and NonResourceAuthorizationAttributes must be set
 */
@GeneratedDsl
data class SubjectAccessReviewSpec(
    /**
     * Extra corresponds to the user.Info.GetExtra() method from the authenticator.
     * Since that is input to the authorizer it needs a reflection here.
     */
    val extra: Map<String, List<String>>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * Groups is the groups you're testing for.
     */
    val groups: List<String>? = null,
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
     */
    val resourceAttributes: ResourceAttributes? = null,
    /**
     * uid (string)
     *
     * UID information about the requesting user.
     */
    val uid: String? = null,
    /**
     * user (string)
     *
     * User is the user you're testing for. If you specify "User"
     * but not "Groups", then is it interpreted as "What if User were not a
     * member of any groups
     */
    val user: String? = null
)

