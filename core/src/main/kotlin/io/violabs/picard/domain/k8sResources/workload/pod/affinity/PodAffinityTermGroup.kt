package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BuilderGroup

class PodAffinityTermGroup : BuilderGroup<PodAffinityTerm, PodAffinityTerm.Builder>(PodAffinityTerm.Builder()) {
    fun terms(): MutableList<PodAffinityTerm>? = items()

    fun term(scope: PodAffinityTerm.Builder.() -> Unit) = add(scope)
}