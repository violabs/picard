package io.violabs.picard.v2.resources.configstorage.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Represents a reference to a PersistentVolumeClaim in the
 * same namespace. More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims
 *
 * PersistentVolumeClaimVolumeSource references the user's PVC in the same namespace.
 * This volume finds the bound PV and mounts that volume for the pod. A PersistentVolumeClaimVolumeSource
 * is, essentially, a wrapper around another type of volume that is owned by someone else (the system).
 */
@GeneratedDsl
data class PersistentVolumeClaimVolumeSource(
    /**
     * The name of a PersistentVolumeClaim in the same namespace as the pod using this volume.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims
     */
    val claimName: String,
    /**
     * Will force the ReadOnly setting in VolumeMounts. Default false.
     */
    val readOnly: Boolean? = null
)