package io.violabs.picard.domain.k8sResources.workload.pod.affinity

class NodeAffinityPreferredSchedulingTermGroup {
    private var preferredDuringSchedulingIgnoredDuringExecution: MutableList<NodeAffinityPreferredSchedulingTerm> = mutableListOf()

    fun executions(): List<NodeAffinityPreferredSchedulingTerm>? {
        return preferredDuringSchedulingIgnoredDuringExecution.takeIf { it.isNotEmpty() }
    }

    fun term(scope: NodeAffinityPreferredSchedulingTerm.Builder.() -> Unit) {
        preferredDuringSchedulingIgnoredDuringExecution.add(NodeAffinityPreferredSchedulingTerm.Builder().apply(scope).build())
    }
}