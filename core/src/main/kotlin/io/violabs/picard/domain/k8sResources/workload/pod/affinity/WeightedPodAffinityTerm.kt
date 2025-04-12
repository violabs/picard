package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinityTerm
import io.violabs.picard.domain.DslBuilder

data class WeightedPodAffinityTerm(
    val podAffinityTerm: PodAffinityTerm,
    val weight: Int
) : BaseAffinityTerm {
    class Builder : DslBuilder<WeightedPodAffinityTerm> {
        private var _podAffinityTerm: PodAffinityTerm? = null
        var weight: Int? = null

        fun podAffinityTerm(): PodAffinityTerm? = _podAffinityTerm

        fun podAffinityTerm(init: PodAffinityTerm.Builder.() -> Unit) {
            _podAffinityTerm = PodAffinityTerm.Builder().apply(init).build()
        }

        override fun build(): WeightedPodAffinityTerm {
            return WeightedPodAffinityTerm(
                podAffinityTerm = requireNotNull(_podAffinityTerm),
                weight = requireNotNull(weight)
            )
        }
    }
}