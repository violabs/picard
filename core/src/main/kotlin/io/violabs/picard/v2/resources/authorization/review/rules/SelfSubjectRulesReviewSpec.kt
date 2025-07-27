package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SelfSubjectRulesReviewSpec defines the specification for SelfSubjectRulesReview.
 */
@GeneratedDsl
data class SelfSubjectRulesReviewSpec(
    /**
     * Namespace to evaluate rules for. Required.
     */
    val namespace: String? = null
)
