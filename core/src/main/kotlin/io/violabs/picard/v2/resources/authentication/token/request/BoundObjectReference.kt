package io.violabs.picard.v2.resources.authentication.token.request

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion

/**
 * BoundObjectReference is a reference to an object that a token is bound to.
 */
@GeneratedDsl
data class BoundObjectReference(
    /**
     * API version of the referent.
     */
    val apiVersion: APIVersion? = null,
    /**
     * Kind of the referent. Valid kinds are 'Pod' and 'Secret'.
     */
    val kind: Kind? = null,
    /**
     * Name of the referent.
     */
    val name: String? = null,
    /**
     * UID of the referent.
     */
    val uid: String? = null
) {
    enum class Kind {
        Pod,
        Secret
    }
}

