package io.violabs.picard.v2.resources.workload.pod.affinity

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * An empty preferred scheduling term matches all objects with implicit weight 0
 * (i.e. it's a no-op). A null preferred scheduling term matches no objects
 * (i.e. is also a no-op).
 */
@GeneratedDsl(withListGroup = true)
data class PreferredSchedulingTerm(
    /**
     * Weight associated with matching the corresponding nodeSelectorTerm, in the range 1-100.
     */
    val weight: Int,
    /**
     * A node selector term, associated with the corresponding weight.
     */
    val preference: NodeSelectorTerm
)