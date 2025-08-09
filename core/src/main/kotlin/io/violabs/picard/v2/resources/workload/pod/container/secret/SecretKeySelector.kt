package io.violabs.picard.v2.resources.workload.pod.container.secret

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SecretKeySelector selects a key of a Secret.
 */
@GeneratedDsl
data class SecretKeySelector(
    /**
     * The key of the secret to select from. Must be a valid secret key.
     */
    val key: String,
    /**
     * Name of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * Specify whether the Secret or its key must be defined
     */
    val optional: Boolean? = null
)