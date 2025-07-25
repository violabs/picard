package io.violabs.picard.v2.resources.storage.csi.node

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 * VolumeNodeResources is a set of resource limits for scheduling of volumes.
 */
@SingleEntryTransformDsl<Int>(Int::class)
data class VolumeNodeResources(
    /**
     * count indicates the maximum number of unique volumes managed by the
     * CSI driver that can be used on a node. A volume that is both attached
     * and mounted on a node is considered to be used once, not twice. The
     * same rule applies for a unique volume that is shared among multiple
     * pods on the same node. If this field is not specified, then the supported
     * number of volumes on this node is unbounded.
     */
    val count: Int? = null
)