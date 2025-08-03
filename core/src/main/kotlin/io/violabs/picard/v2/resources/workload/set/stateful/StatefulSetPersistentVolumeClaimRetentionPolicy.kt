package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * StatefulSetPersistentVolumeClaimRetentionPolicy describes the policy used
 * for PVCs created from the StatefulSet VolumeClaimTemplates.
 */
@GeneratedDsl
data class StatefulSetPersistentVolumeClaimRetentionPolicy(
    /**
     * WhenDeleted specifies what happens to PVCs created from StatefulSet
     * VolumeClaimTemplates when the StatefulSet is deleted. The default policy of
     * Retain causes PVCs to not be affected by StatefulSet deletion. The Delete
     * policy causes those PVCs to be deleted.
     */
    val whenDeleted: String? = null,
    /**
     * WhenScaled specifies what happens to PVCs created from StatefulSet
     * VolumeClaimTemplates when the StatefulSet is scaled down. The default policy of
     * Retain causes PVCs to not be affected by a scaledown. The Delete policy causes
     * the associated PVCs for any excess pods above the replica count to be deleted.
     */
    val whenScaled: String? = null
)