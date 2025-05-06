package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseAffinityTerm
import io.violabs.picard.common.DSLBuilder

data class WeightedPodAffinityTerm(
    val podAffinityTerm: PodAffinityTerm,
    val weight: Int
) : BaseAffinityTerm {
    class Builder : DSLBuilder<WeightedPodAffinityTerm> {
        private var podAffinityTerm: PodAffinityTerm? = null
        var weight: Int? = null

        fun podAffinityTerm(init: PodAffinityTerm.Builder.() -> Unit) {
            podAffinityTerm = PodAffinityTerm.Builder().apply(init).build()
        }

        override fun build(): WeightedPodAffinityTerm {
            return WeightedPodAffinityTerm(
                podAffinityTerm = vRequireNotNull(this::podAffinityTerm),
                weight = vRequireNotNull(this::weight)
            )
        }
    }

    class Group : BuilderGroup<WeightedPodAffinityTerm, Builder>(Builder()) {
        fun terms(): List<WeightedPodAffinityTerm>? = items()

        fun addWeightedPodAffinityTerm(block: Builder.() -> Unit) {
            add(block)
        }
    }
}