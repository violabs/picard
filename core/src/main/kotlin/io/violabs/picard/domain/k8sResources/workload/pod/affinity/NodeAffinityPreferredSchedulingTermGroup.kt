package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BuilderGroup

class NodeAffinityPreferredSchedulingTermGroup :
    BuilderGroup<NodeAffinityPreferredSchedulingTerm, NodeAffinityPreferredSchedulingTerm.Builder>(
        NodeAffinityPreferredSchedulingTerm.Builder()
    ) {
    fun executions(): List<NodeAffinityPreferredSchedulingTerm>? = items()

    fun term(scope: NodeAffinityPreferredSchedulingTerm.Builder.() -> Unit) = add(scope)
}