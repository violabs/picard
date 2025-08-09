package io.violabs.picard.v2.resources.workload.pod.container.secret

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SecretEnvSource selects a Secret to populate the environment variables with.
 * The contents of the target Secret's Data field will represent the key-value pairs as environment variables.
 */
@GeneratedDsl
data class SecretEnvSource(
    /**
     * Name of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * Specify whether the Secret must be defined
     */
    val optional: Boolean? = null
)