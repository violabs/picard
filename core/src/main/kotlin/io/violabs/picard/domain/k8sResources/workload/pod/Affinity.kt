package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.storage.volume.persistentVolume.PersistentVolume
import io.violabs.picard.domain.k8sResources.workload.pod.PodAffinity.Term

data class Affinity(
    val nodeAffinity: io.violabs.picard.domain.k8sResources.workload.pod.NodeAffinity? = null,
    val podAffinity: io.violabs.picard.domain.k8sResources.workload.pod.PodAffinity? = null,
    val podAntiAffinity: io.violabs.picard.domain.k8sResources.workload.pod.PodAntiAffinity? = null
)

data class NodeAffinity(val preferredDuringSchedulingIgnoredDuringExecution: List<io.violabs.picard.domain.k8sResources.workload.pod.NodeAffinity.PreferredSchedulingTerm>? = null) {
    data class PreferredSchedulingTerm(
        val preference: PersistentVolume.NodeSelector.Term,
        val weight: Int
    )
}

data class PodAffinity(
    val preferredDuringSchedulingIgnoredDuringExecution: List<io.violabs.picard.domain.k8sResources.workload.pod.WeightedPodAffinityTerm>? = null,
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
    val preferredDuringSchedulingIgnoredDuringExecution: List<io.violabs.picard.domain.k8sResources.workload.pod.WeightedPodAffinityTerm>? = null,
    val requiredDuringSchedulingIgnoredDuringExecution: List<Term>? = null,
)