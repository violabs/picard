package io.violabs.picard.v2.resources.policy.resource.quota

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * ResourceQuotaSpec defines the desired hard limits to enforce for Quota.
 */
@GeneratedDsl
data class ResourceQuotaSpec(
    /**
     * hard is the set of desired hard limits for each named resource.
     * More info: https://kubernetes.io/docs/concepts/policy/resource-quotas/
     */
    val hard: Map<String, Quantity>? = null,
    /**
     * scopeSelector is also a collection of filters like scopes that must match each object tracked by a quota
     * but expressed using ScopeSelectorOperator in combination with possible values.
     * For a resource to match, both scopes AND scopeSelector (if specified in spec), must be matched.
     */
    val scopeSelector: ScopeSelector? = null,
    /**
     * A collection of filters that must match each object tracked by a quota.
     * If not specified, the quota matches all objects.
     */
    val scopes: List<String>? = null
)