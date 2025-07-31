package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * Overhead structure represents the resource overhead associated with running a pod.
 */
@GeneratedDsl
data class Overhead(
    /**
     * podFixed represents the fixed resource overhead associated with running a pod.
     */
    val podFixed: Map<String, Quantity>? = null
)