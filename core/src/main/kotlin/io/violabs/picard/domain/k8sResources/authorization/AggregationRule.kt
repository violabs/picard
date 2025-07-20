package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.label.LabelSelector

data class AggregationRule(
    val clusterRoleSelectors: List<LabelSelector>? = null
) {
    class Builder : DslBuilder<AggregationRule> {
        private var clusterRoleSelectors: List<LabelSelector>? = null

        fun clusterRoleSelectors(scope: LabelSelector.Group.() -> Unit) {
            this.clusterRoleSelectors = LabelSelector.Group().apply(scope).selectors()
        }

        override fun build(): AggregationRule {
            return AggregationRule(clusterRoleSelectors)
        }
    }
}
