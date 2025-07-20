package io.violabs.picard.v2.resources.storage.csi.driver

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * TokenRequest contains parameters of a service account token.
 */
@GeneratedDsl(withListGroup = true)
data class TokenRequest(
    /**
     * audience is the intended audience of the token in "TokenRequestSpec". It will
     * default to the audiences of kube apiserver.
     */
    val audience: String,
    /**
     * expirationSeconds is the duration of validity of the token in "TokenRequestSpec".
     * It has the same default value of "ExpirationSeconds" in "TokenRequestSpec".
     */
    val expirationSeconds: Long? = null
)