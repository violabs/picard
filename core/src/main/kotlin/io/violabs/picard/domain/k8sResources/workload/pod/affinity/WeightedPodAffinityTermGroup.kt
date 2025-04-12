package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BuilderGroup

class WeightedPodAffinityTermGroup : BuilderGroup<WeightedPodAffinityTerm, WeightedPodAffinityTerm.Builder>(
    WeightedPodAffinityTerm.Builder()
) {
    fun terms(): MutableList<WeightedPodAffinityTerm>? = items()

    fun term(scope: WeightedPodAffinityTerm.Builder.() -> Unit) = add(scope)
}