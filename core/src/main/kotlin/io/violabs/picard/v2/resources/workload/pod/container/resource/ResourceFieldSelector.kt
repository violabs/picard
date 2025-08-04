package io.violabs.picard.v2.resources.workload.pod.container.resource

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * ResourceFieldSelector represents container resources (cpu, memory) and their output format
 */
@GeneratedDsl
data class ResourceFieldSelector(
    /**
     * Container resource name: required for volumes, optional for env vars
     */
    val resource: String,
    /**
     * Container name: required for volumes, optional for env vars
     */
    val containerName: String? = null,
    /**
     * Specifies the output format of the exposed resources, defaults to "1"
     */
    val divisor: Quantity? = null
)