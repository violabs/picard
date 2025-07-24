package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SecretReference represents a Secret Reference. It has enough information to retrieve secret in any namespace
 */
@GeneratedDsl
data class SecretReference(
    /**
     * name is unique within a namespace to reference a secret resource.
     */
    val name: String? = null,
    /**
     * namespace defines the space within which the secret name must be unique.
     */
    val namespace: String? = null
)