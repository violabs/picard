package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EnvFromSource represents the source of a set of ConfigMaps
 */
@GeneratedDsl(withListGroup = true)
data class EnvFromSource(
    /**
     * The ConfigMap to select from
     */
    val configMapRef: ConfigMapEnvSource? = null,
    /**
     * An optional identifier to prepend to each key in the ConfigMap. Must be a C_IDENTIFIER.
     */
    val prefix: String? = null,
    /**
     * The Secret to select from
     */
    val secretRef: SecretEnvSource? = null
)