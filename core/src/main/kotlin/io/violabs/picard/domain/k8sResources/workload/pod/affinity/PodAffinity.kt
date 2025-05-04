package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.common.DSLBuilder

data class PodAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null
) : BaseAffinity {
    class Builder : DSLBuilder<PodAffinity> {
        private var preferredDuringSchedulingIgnoredDuringExecution: MutableList<WeightedPodAffinityTerm>? = null
        private var requiredDuringSchedulingIgnoredDuringExecution: MutableList<PodAffinityTerm>? = null

        fun preferredDuringSchedulingIgnoredDuringExecution(scope: WeightedPodAffinityTermGroup.() -> Unit) {
            preferredDuringSchedulingIgnoredDuringExecution = WeightedPodAffinityTermGroup().apply(scope).terms()
        }

        fun requiredDuringSchedulingIgnoredDuringExecution(scope: PodAffinityTermGroup.() -> Unit) {
            requiredDuringSchedulingIgnoredDuringExecution = PodAffinityTermGroup().apply(scope).terms()
        }

        override fun build(): PodAffinity {
            return PodAffinity(
                preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution,
                requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution
            )
        }
    }
}