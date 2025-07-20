package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Represents the status object of ControllerModifyVolume operation
 */
@GeneratedDsl
data class ModifyVolumeStatus(
    /**
     * status is the status of the ControllerModifyVolume operation.
     */
    val status: Type,
    /**
     * targetVolumeAttributesClassName is the name of the VolumeAttributesClass the PVC currently
     * being reconciled
     */
    val targetVolumeAttributesClassName: String? = null
) {
    /**
     * Pending Pending indicates that the PersistentVolumeClaim cannot be modified due to unmet requirements, such as the specified VolumeAttributesClass not existing.
     * InProgress InProgress indicates that the volume is being modified.
     * Infeasible Infeasible indicates that the request has been rejected as invalid by the
     *            CSI driver. To resolve the error, a valid VolumeAttributesClass needs to be specified.
     *            Note: New statuses can be added in the future. Consumers should check for unknown
     *            statuses and fail appropriately.
     */
    enum class Type {
        Pending,
        InProgress,
        Infeasible
    }
}