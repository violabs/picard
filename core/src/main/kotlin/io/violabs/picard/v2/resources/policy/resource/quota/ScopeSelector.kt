package io.violabs.picard.v2.resources.policy.resource.quota

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * A scope selector represents the AND of the selectors represented by the scoped-resource selector requirements.
 */
@GeneratedDsl
data class ScopeSelector(
    /**
     * A list of scope selector requirements by scope of the resources.
     */
    val matchExpressions: List<ScopedResourceSelectorRequirement>? = null
)