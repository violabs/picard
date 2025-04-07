package io.violabs.picard.domain.k8sResources.policy

data class ScopeSelector(
    private val matchExpressions: List<ScopedResourceSelectorRequirement>? = null
)