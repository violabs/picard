package io.violabs.picard.v2.resources.policy.resource.quota

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * ResourceQuotaStatus defines the enforced hard limits and observed use.
 */
@GeneratedDsl
data class ResourceQuotaStatus(
    /**
     * Hard is the set of enforced hard limits for each named resource.
     * More info: https://kubernetes.io/docs/concepts/policy/resource-quotas/
     */
    val hard: Map<String, Quantity>? = null,
    /**
     * Used is the current observed total usage of the resource in the namespace.
     */
    val used: Map<String, Quantity>? = null
)