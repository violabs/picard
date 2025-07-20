package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.v2.common.LabelSelector

/**
 * PersistentVolumeClaimSpec describes the common attributes of storage devices and allows a
 * Source for provider-specific attributes
 */
@GeneratedDsl
data class PersistentVolumeClaimSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * accessModes contains the desired access modes the volume should have.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#access-modes-1
     */
    val accessModes: List<AccessMode>? = null,
    /**
     * selector is a label query over volumes to consider for binding.
     */
    val selector: LabelSelector? = null,
    /**
     * resources represents the minimum resources the volume should have. If RecoverVolumeExpansionFailure
     * feature is enabled users are allowed to specify resource requirements that are lower than previous
     * value but must still be higher than capacity recorded in the status field of the claim.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#resources
     */
    val resources: VolumeResourceRequirements? = null,
    /**
     * storageClassName is the name of the StorageClass required by the claim.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#class-1
     */
    val storageClassName: String? = null,
    /**
     * volumeName is the binding reference to the PersistentVolume backing this claim.
     */
    val volumeName: String? = null,
    /**
     * volumeMode defines what type of volume is required by the claim. Value of Filesystem is implied when
     * not included in claim spec.
     */
    val volumeMode: String? = null
) : BaseSpec
