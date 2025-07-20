package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.common.DslBuilder

data class PodAntiAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null,
) : BaseAffinity {
    class Builder : DslBuilder<PodAntiAffinity> {
        private var preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null
        private var requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null

        fun preferredDuringSchedulingIgnoredDuringExecution(scope: WeightedPodAffinityTerm.Group.() -> Unit) {
            preferredDuringSchedulingIgnoredDuringExecution = WeightedPodAffinityTerm.Group().apply(scope).terms()
        }

        fun requiredDuringSchedulingIgnoredDuringExecution(scope: PodAffinityTerm.Group.() -> Unit) {
            requiredDuringSchedulingIgnoredDuringExecution = PodAffinityTerm.Group().apply(scope).terms()
        }

        override fun build(): PodAntiAffinity {
            return PodAntiAffinity(
                preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution,
                requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution
            )
        }
    }
}