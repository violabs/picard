package io.violabs.picard.domain.k8sResources.workload.pod.affinity

class WeightedSchedulingTermGroup {
    private var preferredDuringSchedulingIgnoredDuringExecution: MutableList<WeightedPodAffinityTerm> = mutableListOf()

    fun executions(): List<WeightedPodAffinityTerm>? {
        return preferredDuringSchedulingIgnoredDuringExecution.takeIf { it.isNotEmpty() }
    }

    fun term(scope: WeightedPodAffinityTerm.Builder.() -> Unit) {
        preferredDuringSchedulingIgnoredDuringExecution.add(WeightedPodAffinityTerm.Builder().apply(scope).build())
    }
}