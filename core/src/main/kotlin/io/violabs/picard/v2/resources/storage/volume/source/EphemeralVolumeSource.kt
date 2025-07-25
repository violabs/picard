package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimTemplate

/**
 * Represents a volume that is handled by a cluster storage driver. The volume's lifecycle
 * is tied to the pod that defines it - it will be created before the pod starts, and deleted
 * when the pod is removed.
 *
 * Use this if: a) the volume is only needed while the pod runs, b) features of normal volumes
 * like restoring from snapshot or capacity tracking are needed, c) the storage driver is specified
 * through a storage class, and d) the storage driver supports dynamic volume provisioning through a
 * PersistentVolumeClaim (see EphemeralVolumeSource for more information on the connection between
 * this volume type and PersistentVolumeClaim).
 *
 * Use PersistentVolumeClaim or one of the vendor-specific APIs for volumes that persist for longer
 * than the lifecycle of an individual pod.
 *
 * Use CSI for light-weight local ephemeral volumes if the CSI driver is meant to be used that way -
 * see the documentation of the driver for more information.
 *
 * A pod can use both types of ephemeral volumes and persistent volumes at the same time.
 */
@GeneratedDsl
data class EphemeralVolumeSource(
    val volumeClaimTemplate: PersistentVolumeClaimTemplate
)