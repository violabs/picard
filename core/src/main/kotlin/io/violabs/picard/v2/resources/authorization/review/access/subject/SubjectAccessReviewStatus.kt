package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class SubjectAccessReviewStatus(
    /**
     * Allowed is required. True if the action would be allowed, false otherwise.
     */
    val allowed: Boolean,
    /**
     * Denied is optional. True if the action would be denied, otherwise false.
     * If both allowed is false and denied is false, then the authorizer has no
     * opinion on whether to authorize the action. Denied may not be true if Allowed is true.
     */
    val denied: Boolean? = null,
    /**
     * EvaluationError is an indication that some error occurred during the authorization
     * check. It is entirely possible to get an error and be able to continue determine
     * authorization status in spite of it. For instance, RBAC can be missing a role,
     * but enough roles are still present and bound to reason about the request.
     */
    val evaluationError: String? = null,
    /**
     * Reason is optional. It indicates why a request was allowed or denied.
     */
    val reason: String? = null,
)