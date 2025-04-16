package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseAffinityTerm
import io.violabs.picard.domain.DSLBuilder

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
}