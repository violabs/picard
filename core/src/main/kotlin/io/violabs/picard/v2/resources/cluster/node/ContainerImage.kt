package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Describe a container image
 */
@GeneratedDsl(withListGroup = true)
data class ContainerImage(
    /**
     * Atomic: will be replaced during a merge
     *
     * Names by which this image is known. e.g.
     * ["kubernetes.example/hyperkube:v1.0.7", "cloud-vendor.registry.example/cloud-vendor/hyperkube:v1.0.7"]
     */
    val names: List<String>? = null,
    /**
     * The size of the image in bytes.
     */
    val sizeBytes: Long? = null
)