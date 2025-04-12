package io.violabs.picard.domain.k8sResources.workload.pod.affinity

class WeightedPodAffinityTermGroup {
    private var terms: MutableList<WeightedPodAffinityTerm> = mutableListOf()

    fun terms(): MutableList<WeightedPodAffinityTerm>? {
        return terms.takeIf { it.isNotEmpty() }
    }

    fun term(scope: WeightedPodAffinityTerm.Builder.() -> Unit) {
        terms.add(WeightedPodAffinityTerm.Builder().apply(scope).build())
    }
}