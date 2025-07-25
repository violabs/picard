package io.violabs.picard.v2.resources.storage.persistent.volume.source

import io.violabs.picard.v2.common.SecretReference

/**
 * Represents storage that is managed by an external CSI volume driver
 */
class CsiPersistentVolumeSource(
    /**
     * driver is the name of the driver to use for this volume. Required.
     */
    val driver: String,
    /**
     * volumeHandle is the unique volume name returned by the CSI volume plugin’s
     * CreateVolume to refer to the volume on all subsequent calls. Required.
     */
    val volumeHandle: String,
    /**
     * controllerExpandSecretRef is a reference to the secret object containing sensitive information to
     * pass to the CSI driver to complete the CSI ControllerExpandVolume call. This field is optional,
     * and may be empty if no secret is required. If the secret object contains more than one secret,
     * all secrets are passed.
     */
    val controllerExpandSecretRef: SecretReference? = null,
    /**
     * controllerPublishSecretRef is a reference to the secret object containing sensitive
     * information to pass to the CSI driver to complete the CSI ControllerPublishVolume and
     * ControllerUnpublishVolume calls. This field is optional, and may be empty if no secret
     * is required. If the secret object contains more than one secret, all secrets are passed.
     */
    val controllerPublishSecretRef: SecretReference? = null,
    /**
     * fsType to mount. Must be a filesystem type supported by the host operating
     * system. Ex. "ext4", "xfs", "ntfs".
     */
    val fsType: String? = null,
    /**
     * nodeExpandSecretRef is a reference to the secret object containing sensitive information
     * to pass to the CSI driver to complete the CSI NodeExpandVolume call. This field is optional,
     * may be omitted if no secret is required. If the secret object contains more than one secret,
     * all secrets are passed.
     */
    val nodeExpandSecretRef: SecretReference? = null,
    /**
     * nodePublishSecretRef is a reference to the secret object containing sensitive information
     * to pass to the CSI driver to complete the CSI NodePublishVolume and NodeUnpublishVolume calls.
     * This field is optional, and may be empty if no secret is required. If the secret object contains
     * more than one secret, all secrets are passed.
     */
    val nodePublishSecretRef: SecretReference? = null,
    /**
     * nodeStageSecretRef is a reference to the secret object containing sensitive information
     * to pass to the CSI driver to complete the CSI NodeStageVolume and NodeStageVolume and
     * NodeUnstageVolume calls. This field is optional, and may be empty if no secret is required.
     * If the secret object contains more than one secret, all secrets are passed.
    */
    val nodeStageSecretRef: SecretReference? = null,
    /**
     * readOnly value to pass to ControllerPublishVolumeRequest. Defaults to false (read/write).
     */
    val readOnly: Boolean? = null,
    /**
     * volumeAttributes of the volume to publish.
     */
    val volumeAttributes: Map<String, String>? = null
)