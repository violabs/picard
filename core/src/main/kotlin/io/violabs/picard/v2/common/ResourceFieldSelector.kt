package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/resource-field-selector/#ResourceFieldSelector
 *
 * import "k8s.io/api/core/v1"
 *
 * ResourceFieldSelector represents container resources (cpu, memory) and their output format
 */
@GeneratedDsl
data class ResourceFieldSelector(
    /**
     * Resource to select
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