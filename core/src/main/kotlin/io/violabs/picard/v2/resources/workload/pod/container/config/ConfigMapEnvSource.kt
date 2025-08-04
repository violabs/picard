package io.violabs.picard.v2.resources.workload.pod.container.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ConfigMapEnvSource selects a ConfigMap to populate the environment variables with.
 * The contents of the target ConfigMap's Data field will represent the key-value pairs as environment variables.
 */
@GeneratedDsl
data class ConfigMapEnvSource(
    /**
     * Name of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * Specify whether the ConfigMap must be defined
     */
    val optional: Boolean? = null
)