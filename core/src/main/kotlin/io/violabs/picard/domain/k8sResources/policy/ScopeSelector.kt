package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.DSLBuilder

data class ScopeSelector(
    private val matchExpressions: List<ScopedResourceSelectorRequirement>? = null
) {
    class Builder : DSLBuilder<ScopeSelector> {
        private var matchExpressions: List<ScopedResourceSelectorRequirement>? = null

        fun matchExpressions(scope: ScopedResourceSelectorRequirement.Group.() -> Unit) {
            this.matchExpressions = ScopedResourceSelectorRequirement.Group().apply(scope).requirements()
        }

        override fun build(): ScopeSelector {
            return ScopeSelector(
                matchExpressions = matchExpressions
            )
        }
    }
}