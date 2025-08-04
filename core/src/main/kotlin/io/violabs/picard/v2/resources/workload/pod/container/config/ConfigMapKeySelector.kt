package io.violabs.picard.v2.resources.workload.pod.container.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Selects a key from a ConfigMap.
 */
@GeneratedDsl
data class ConfigMapKeySelector(
    /**
     * The key to select.
     */
    val key: String,
    /**
     * Name of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * Specify whether the ConfigMap or its key must be defined
     */
    val optional: Boolean? = null
)