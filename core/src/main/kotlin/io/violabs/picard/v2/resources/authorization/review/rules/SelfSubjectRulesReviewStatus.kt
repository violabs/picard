package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SubjectRulesReviewStatus contains the result of a rules check.
 * This check can be incomplete depending on the set of authorizers
 * the server is configured with and any errors experienced during
 * evaluation. Because authorization rules are additive, if a rule
 * appears in a list it's safe to assume the subject has that
 * permission, even if that list is incomplete.
 */
@GeneratedDsl
data class SelfSubjectRulesReviewStatus(
    /**
     * Incomplete is true when the rules returned by this call are
     * incomplete. This is most commonly encountered when an authorizer,
     * such as an external authorizer, doesn't support rules evaluation.
     */
    val incomplete: Boolean,
    /**
     * Atomic: will be replaced during a merge
     *
     * NonResourceRules is the list of actions the subject is allowed to
     * perform on non-resources. The list ordering isn't significant, may
     * contain duplicates, and possibly be incomplete.
     *
     * NonResourceRule holds information that describes a rule for the non-resource
     */
    val nonResourceRules: List<NonResourceRule>,
    /**
     * Atomic: will be replaced during a merge
     *
     * ResourceRules is the list of actions the subject is allowed to perform on resources.
     * The list ordering isn't significant, may contain duplicates, and possibly be incomplete.
     *
     * ResourceRule is the list of actions the subject is allowed to perform on resources.
     * The list ordering isn't significant, may contain duplicates, and possibly be incomplete.
     */
    val resourceRules: List<ResourceRule>,
    /**
     * status.evaluationError (string)
     *
     * EvaluationError can appear in combination with Rules. It indicates an error
     * occurred during rule evaluation, such as an authorizer that doesn't support
     * rule evaluation, and that ResourceRules and/or NonResourceRules may be incomplete.
     */
    val evaluationError: String? = null
)

