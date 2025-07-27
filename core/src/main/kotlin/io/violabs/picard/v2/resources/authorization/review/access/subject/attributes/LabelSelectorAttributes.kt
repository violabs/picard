package io.violabs.picard.v2.resources.authorization.review.access.subject.attributes

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelectorRequirement

/**
 * LabelSelectorAttributes indicates a label limited access.
 */
@GeneratedDsl
data class LabelSelectorAttributes(
    /**
     * resourceAttributes.labelSelector.rawSelector (string)
     *
     * rawSelector is the serialization of a field selector that would be included in a query parameter. Webhook implementations are encouraged to ignore rawSelector. The kube-apiserver's *SubjectAccessReview will parse the rawSelector as long as the requirements are not present.
     */
    val rawSelector: String? = null,
    /**
     * resourceAttributes.labelSelector.requirements ([]LabelSelectorRequirement)
     *
     * Atomic: will be replaced during a merge
     *
     * requirements is the parsed interpretation of a label selector. All requirements must be met for a resource instance to match the selector. Webhook implementations should handle requirements, but how to handle them is up to the webhook. Since requirements can only limit the request, it is safe to authorize as unlimited request if the requirements are not understood.
     *
     * A label selector requirement is a selector that contains values, a key, and an operator that relates the key and values.
     */
    val requirements: List<LabelSelectorRequirement>? = null
)

