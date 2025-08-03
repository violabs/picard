package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Affinity is a group of affinity scheduling rules.
 */
@GeneratedDsl
data class Affinity(
    /**
     * Describes node affinity scheduling rules for the pod.
     */
    val nodeAffinity: NodeAffinity? = null,
    /**
     * Describes pod affinity scheduling rules (e.g. co-locate this pod in the same node, zone, etc. as some other pod(s)).
     */
    val podAffinity: PodAffinity? = null,
    /**
     * Describes pod anti-affinity scheduling rules (e.g. avoid putting this pod in the same node, zone, etc. as some other pod(s)).
     */
    val podAntiAffinity: PodAntiAffinity? = null
)

@GeneratedDsl
data class NodeAffinity(
    val requiredDuringSchedulingIgnoredDuringExecution: NodeSelector? = null,
    val preferredDuringSchedulingIgnoredDuringExecution: List<PreferredSchedulingTerm>? = null
)

@GeneratedDsl
data class NodeSelector(
    val nodeSelectorTerms: List<NodeSelectorTerm>
)

@GeneratedDsl(withListGroup = true)
data class NodeSelectorTerm(
    val matchExpressions: List<NodeSelectorRequirement>? = null,
    val matchFields: List<NodeSelectorRequirement>? = null
)

@GeneratedDsl(withListGroup = true)
data class NodeSelectorRequirement(
    val key: String,
    val operator: String,
    val values: List<String>? = null
)

@GeneratedDsl(withListGroup = true)
data class PreferredSchedulingTerm(
    val weight: Int,
    val preference: NodeSelectorTerm
)

@GeneratedDsl
data class PodAffinity(
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null,
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null
)

@GeneratedDsl
data class PodAntiAffinity(
    val requiredDuringSchedulingIgnoredDuringExecution: List<PodAffinityTerm>? = null,
    val preferredDuringSchedulingIgnoredDuringExecution: List<WeightedPodAffinityTerm>? = null
)

@GeneratedDsl(withListGroup = true)
data class PodAffinityTerm(
    val labelSelector: io.violabs.picard.v2.common.LabelSelector? = null,
    val namespaceSelector: io.violabs.picard.v2.common.LabelSelector? = null,
    val namespaces: List<String>? = null,
    val topologyKey: String,
    val matchLabelKeys: List<String>? = null,
    val mismatchLabelKeys: List<String>? = null
)

@GeneratedDsl(withListGroup = true)
data class WeightedPodAffinityTerm(
    val weight: Int,
    val podAffinityTerm: PodAffinityTerm
)