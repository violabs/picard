package io.violabs.picard.v2.resources.authorization.review.access.subject.attributes

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NonResourceAttributes includes the authorization attributes available
 * for non-resource requests to the Authorizer interface
 */
@GeneratedDsl
data class NonResourceAttributes(
    /**
     * nonResourceAttributes.path (string)
     *
     * Path is the URL path of the request
     */
    val path: String? = null,
    /**
     * nonResourceAttributes.verb (string)
     *
     * Verb is the standard HTTP verb
     */
    val verb: String? = null
)