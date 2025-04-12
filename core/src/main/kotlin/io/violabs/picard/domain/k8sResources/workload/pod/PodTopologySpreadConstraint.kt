package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.LabelSelector

data class TopologySpreadConstraint(
    val maxSkew: Int,
    val topologyKey: String,
    val whenUnsatisfiable: String,
    val labelSelector: LabelSelector? = null,
    val matchLabelKeys: List<String>? = null,
    val minDomains: Int? = null,
    val nodeAffinityPolicy: String? = null,
    val nodeTaintsPolicy: String? = null
) {
    class Builder : DslBuilder<TopologySpreadConstraint> {
        var maxSkew: Int? = null
        var topologyKey: String? = null
        var whenUnsatisfiable: String? = null
        private var labelSelector: LabelSelector? = null
        private var matchLabelKeys: List<String>? = null
        var minDomains: Int? = null
        var nodeAffinityPolicy: String? = null
        var nodeTaintsPolicy: String? = null

        fun labelSelector(init: LabelSelector.Builder.() -> Unit) {
            labelSelector = LabelSelector.Builder().apply(init).build()
        }

        fun matchLabelKeys(vararg keys: String) {
            matchLabelKeys = keys.toList()
        }

        override fun build(): TopologySpreadConstraint  {
            return TopologySpreadConstraint(
                maxSkew = requireNotNull(maxSkew),
                topologyKey = requireNotNull(topologyKey),
                whenUnsatisfiable = requireNotNull(whenUnsatisfiable),
                labelSelector = labelSelector,
                matchLabelKeys = matchLabelKeys,
                minDomains = minDomains,
                nodeAffinityPolicy = nodeAffinityPolicy,
                nodeTaintsPolicy = nodeTaintsPolicy
            )
        }
    }
}