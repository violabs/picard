package io.violabs.picard.v2.resources.workload.pod.container.resource

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * ResourceRequirements describes the compute resource requirements.
 */
@GeneratedDsl
data class ResourceRequirements(
    /**
     * Claims lists the names of resources, defined in spec.resourceClaims,
     * that are used by this container.
     *
     * This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
     *
     * This field is immutable. It can only be set for containers.
     */
    val claims: List<ResourceClaim>? = null,
    /**
     * Limits describes the maximum amount of compute resources allowed.
     * More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/
     */
    val limits: Map<String, Quantity>? = null,
    /**
     * Requests describes the minimum amount of compute resources required.
     * If Requests is omitted for a container, it defaults to Limits if that is explicitly specified,
     * otherwise to an implementation-defined value.
     * More info: https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/
     */
    val requests: Map<String, Quantity>? = null
)