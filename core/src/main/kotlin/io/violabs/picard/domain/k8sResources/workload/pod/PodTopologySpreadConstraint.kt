package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.label.LabelSelector

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
    class Builder : DSLBuilder<TopologySpreadConstraint> {
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
                maxSkew = vRequireNotNull(this::maxSkew),
                topologyKey = vRequireNotNull(this::topologyKey),
                whenUnsatisfiable = vRequireNotNull(this::whenUnsatisfiable),
                labelSelector = labelSelector,
                matchLabelKeys = matchLabelKeys,
                minDomains = minDomains,
                nodeAffinityPolicy = nodeAffinityPolicy,
                nodeTaintsPolicy = nodeTaintsPolicy
            )
        }
    }
}