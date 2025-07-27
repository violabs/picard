package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class NonResourceRule(
    /**
     * Atomic: will be replaced during a merge
     *
     * Verb is a list of kubernetes non-resource API verbs,
     * like: get, post, put, delete, patch, head, options. "*" means all.
     */
    val verbs: List<String>,
    /**
     * Atomic: will be replaced during a merge
     *
     * NonResourceURLs is a set of partial urls that a user should have access to.
     * s are allowed, but only as the full, final step in the path. "" means all.
     */
    val nonResourceURLs: List<String>? = null
)

