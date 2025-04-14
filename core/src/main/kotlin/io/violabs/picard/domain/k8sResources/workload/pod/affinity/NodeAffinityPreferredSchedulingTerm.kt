package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelectorTerm

data class NodeAffinityPreferredSchedulingTerm(
    val preference: NodeSelectorTerm,
    val weight: Int
) {
    class Builder : DslBuilder<NodeAffinityPreferredSchedulingTerm> {
        private var preference: NodeSelectorTerm? = null
        var weight: Int? = null

        fun preference(init: NodeSelectorTerm.Builder.() -> Unit) {
            preference = NodeSelectorTerm.Builder().apply(init).build()
        }

        override fun build(): NodeAffinityPreferredSchedulingTerm {
            return NodeAffinityPreferredSchedulingTerm(
                preference = vRequireNotNull(this::preference),
                weight = vRequireNotNull(this::weight)
            )
        }
    }
}