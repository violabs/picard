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
     * Describes pod affinity scheduling rules
     * (e.g. co-locate this pod in the same node, zone, etc. as some other pod(s)).
     */
    val podAffinity: PodAffinity? = null,
    /**
     * Describes pod anti-affinity scheduling rules
     * (e.g. avoid putting this pod in the same node, zone, etc. as some other pod(s)).
     */
    val podAntiAffinity: PodAntiAffinity? = null
)