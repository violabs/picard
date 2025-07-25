package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * VolumeNodeAffinity defines constraints that limit what nodes this
 * volume can be accessed from.
 */
@GeneratedDsl
data class VolumeNodeAffinity(
    /**
     * required specifies hard node constraints that must be met.
     *
     * A node selector represents the union of the results of one or more label queries
     * over a set of nodes; that is, it represents the OR of the selectors represented
     * by the node selector terms.
     */
    val required: NodeSelector? = null
)