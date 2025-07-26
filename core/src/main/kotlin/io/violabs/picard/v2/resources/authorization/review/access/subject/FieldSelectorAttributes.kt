package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * FieldSelectorAttributes indicates a field limited access.
 */
@GeneratedDsl
data class FieldSelectorAttributes(
    /**
     * rawSelector is the serialization of a field selector that would
     * be included in a query parameter. Webhook implementations are
     * encouraged to ignore rawSelector. The kube-apiserver's
     * *SubjectAccessReview will parse the rawSelector as long as
     * the requirements are not present.
     */
    val rawSelector: String? = null,
    /**
     * resourceAttributes.fieldSelector.requirements ([]FieldSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * requirements is the parsed interpretation of a field selector. All requirements must be met for a resource instance to match the selector. Webhook implementations should handle requirements, but how to handle them is up to the webhook. Since requirements can only limit the request, it is safe to authorize as unlimited request if the requirements are not understood.
     */
    val requirements: List<FieldSelectorRequirement>? = null
)