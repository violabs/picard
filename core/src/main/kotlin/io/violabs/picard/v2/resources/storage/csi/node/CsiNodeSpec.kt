package io.violabs.picard.v2.resources.storage.csi.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CSINodeSpec holds information about the specification of all
 * CSI drivers installed on a node
 */
@GeneratedDsl
data class CsiNodeSpec(
    /**
     * Patch strategy: merge on key name
     *
     * Map: unique values on key name will be kept during a merge
     *
     * drivers is a list of information of all CSI Drivers existing on a node. If all
     * drivers in the list are uninstalled, this can become empty.
     *
     * CSINodeDriver holds information about the specification of one CSI driver
     * installed on a node
     */
    val drivers: List<CsiNodeDriver>
)