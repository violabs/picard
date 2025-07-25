package io.violabs.picard.v2.resources.authentication.token.review

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * TokenReviewSpec is a description of the token authentication request.
 */
@GeneratedDsl
data class TokenReviewSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * Audiences is a list of the identifiers that the resource server presented
     * with the token identifies as. Audience-aware token authenticators will
     * verify that the token was intended for at least one of the audiences in
     * this list. If no audiences are provided, the audience will default to the
     * audience of the Kubernetes apiserver.
     */
    val audiences: List<String>? = null,
    /**
     * Token is the opaque bearer token.
     */
    val token: String? = null
)

