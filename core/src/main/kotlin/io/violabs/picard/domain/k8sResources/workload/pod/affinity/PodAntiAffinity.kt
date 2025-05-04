package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.common.DSLBuilder

data class PodAntiAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null,
) : BaseAffinity {
    class Builder : DSLBuilder<PodAntiAffinity> {
        private var preferredDuringSchedulingIgnoredDuringExecution: MutableList<WeightedPodAffinityTerm>? = null
        private var requiredDuringSchedulingIgnoredDuringExecution: MutableList<PodAffinityTerm>? = null

        fun preferredDuringSchedulingIgnoredDuringExecution(scope: WeightedPodAffinityTermGroup.() -> Unit) {
            preferredDuringSchedulingIgnoredDuringExecution = WeightedPodAffinityTermGroup().apply(scope).terms()
        }

        fun requiredDuringSchedulingIgnoredDuringExecution(scope: PodAffinityTermGroup.() -> Unit) {
            requiredDuringSchedulingIgnoredDuringExecution = PodAffinityTermGroup().apply(scope).terms()
        }

        override fun build(): PodAntiAffinity {
            return PodAntiAffinity(
                preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution,
                requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution
            )
        }
    }
}