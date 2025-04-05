package io.violabs.picard.domain.k8sResources.pod

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.PersistentVolume
import io.violabs.picard.domain.k8sResources.pod.PodAffinity.Term

data class Affinity(
    val nodeAffinity: NodeAffinity? = null,
    val podAffinity: PodAffinity? = null,
    val podAntiAffinity: PodAntiAffinity? = null
)

data class NodeAffinity(val preferredDuringSchedulingIgnoredDuringExecution: List<PreferredSchedulingTerm>? = null) {
    data class PreferredSchedulingTerm(
        val preference: PersistentVolume.NodeSelectorTerm,
        val weight: Int
    )
}

data class PodAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<Term>? = null
) {
    data class Term(
        val topologyKey: String,
        val labelSelector: LabelSelector? = null,
        val matchLabelKeys: List<String>? = null,
        val mismatchLabelKeys: List<String>? = null,
        val namespaceSelector: LabelSelector? = null,
        val namespaces: List<String>? = null
    )
}

data class WeightedPodAffinityTerm(
    val podAffinityTerm: Term,
    val weight: Int
)

data class PodAntiAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<Term>? = null,
)