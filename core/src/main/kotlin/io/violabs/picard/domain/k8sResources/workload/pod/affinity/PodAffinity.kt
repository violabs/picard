package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.common.DSLBuilder

data class PodAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null
) : BaseAffinity {
    class Builder : DSLBuilder<PodAffinity> {
        private var preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null
        private var requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null

        fun preferredDuringSchedulingIgnoredDuringExecution(scope: WeightedPodAffinityTerm.Group.() -> Unit) {
            preferredDuringSchedulingIgnoredDuringExecution = WeightedPodAffinityTerm.Group().apply(scope).terms()
        }

        fun requiredDuringSchedulingIgnoredDuringExecution(scope: PodAffinityTerm.Group.() -> Unit) {
            requiredDuringSchedulingIgnoredDuringExecution = PodAffinityTerm.Group().apply(scope).terms()
        }

        override fun build(): PodAffinity {
            return PodAffinity(
                preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution,
                requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution
            )
        }
    }
}