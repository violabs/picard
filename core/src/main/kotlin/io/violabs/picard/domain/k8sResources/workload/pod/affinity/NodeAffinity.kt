package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.domain.DSLBuilder

data class NodeAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<NodeAffinityPreferredSchedulingTerm>? = null
) : BaseAffinity {
    class Builder : DSLBuilder<NodeAffinity> {
        private var preferredDuringSchedulingIgnoredDuringExecution: List<NodeAffinityPreferredSchedulingTerm>? = null

        fun preferredDuringSchedulingIgnoredDuringExecution(scope: NodeAffinityPreferredSchedulingTermGroup.() -> Unit) {
            preferredDuringSchedulingIgnoredDuringExecution = NodeAffinityPreferredSchedulingTermGroup().apply(scope).executions()
        }

        override fun build(): NodeAffinity {
            return NodeAffinity(
                preferredDuringSchedulingIgnoredDuringExecution
            )
        }
    }
}