package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * PersistentVolumeClaimStatus is the current status of a persistent volume claim.
 */
@GeneratedDsl
data class PersistentVolumeClaimStatus(
    /**
     * Atomic: will be replaced during a merge
     *
     * accessModes contains the actual access modes the volume backing the PVC has.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#access-modes-1
     */
    val accessModes: List<AccessMode>? = null,
    /**
     * allocatedResourceStatuses stores status of resource being resized for the given PVC. Key names
     * follow standard Kubernetes label syntax. Valid values are either: * Un-prefixed keys: - storage -
     * the capacity of the volume. * Custom resources must use implementation-defined prefixed names such as
     * "example.com/my-custom-resource" Apart from above values - keys that are unprefixed or have
     * kubernetes.io prefix are considered reserved and hence may not be used.
     *
     * ClaimResourceStatus can be in any of following states:
     * - ControllerResizeInProgress: State set when resize controller starts resizing the volume in control-plane.
     * - ControllerResizeFailed: State set when resize has failed in resize controller with a terminal error.
     * - NodeResizePending: State set when resize controller has finished resizing the volume but further
     *   resizing of volume is needed on the node.
     * - NodeResizeInProgress: State set when kubelet starts resizing the volume.
     * - NodeResizeFailed: State set when resizing has failed in kubelet with a terminal error.
     *   Transient errors don't set NodeResizeFailed. For example: if expanding a PVC for more
     *   capacity - this field can be one of the following states:
     *      - pvc.status.allocatedResourceStatus['storage'] = "ControllerResizeInProgress"
     *      - pvc.status.allocatedResourceStatus['storage'] = "ControllerResizeFailed"
     *      - pvc.status.allocatedResourceStatus['storage'] = "NodeResizePending"
     *      - pvc.status.allocatedResourceStatus['storage'] = "NodeResizeInProgress"
     *      - pvc.status.allocatedResourceStatus['storage'] = "NodeResizeFailed" When this field is not set,
     *          it means that no resize operation is in progress for the given PVC.
     *
     * A controller that receives PVC update with previously unknown resourceName or ClaimResourceStatus
     * should ignore the update for the purpose it was designed.
     * For example - a controller that only is responsible for resizing capacity of the volume,
     * should ignore PVC updates that change other valid resources associated with PVC.
     *
     * This is an alpha field and requires enabling RecoverVolumeExpansionFailure feature.
     */
    val allocatedResourceStatuses: Map<String, String>? = null,
    /**
     * allocatedResources tracks the resources allocated to a PVC including its capacity. Key names follow
     * standard Kubernetes label syntax. Valid values are either: * Un-prefixed keys: - storage -
     * the capacity of the volume. * Custom resources must use implementation-defined prefixed names such
     * as "example.com/my-custom-resource" Apart from above values - keys that are unprefixed or have
     * kubernetes.io prefix are considered reserved and hence may not be used.
     *
     * Capacity reported here may be larger than the actual capacity when a volume expansion operation
     * is requested. For storage quota, the larger value from allocatedResources and PVC.spec.resources
     * is used. If allocatedResources is not set, PVC.spec.resources alone is used for quota calculation.
     * If a volume expansion capacity request is lowered, allocatedResources is only lowered if there are no
     * expansion operations in progress and if the actual volume capacity is equal or lower than the
     * requested capacity.
     *
     * A controller that receives PVC update with previously unknown resourceName should ignore the update
     * for the purpose it was designed. For example - a controller that only is responsible for resizing
     * capacity of the volume, should ignore PVC updates that change other valid resources associated with PVC.
     *
     * This is an alpha field and requires enabling RecoverVolumeExpansionFailure feature.
     */
    val allocatedResources: Map<String, Quantity>? = null,
    /**
     * capacity represents the actual resources of the underlying volume.
     */
    val capacity: Map<String, Quantity>? = null,
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * conditions is the current Condition of persistent volume claim. If underlying persistent volume
     * is being resized then the Condition will be set to 'Resizing'.
     *
     * PersistentVolumeClaimCondition contains details about state of pvc
     */
    val conditions: List<PersistentVolumeClaimCondition>? = null,
    /**
     * currentVolumeAttributesClassName is the current name of the VolumeAttributesClass the PVC is
     * using. When unset, there is no VolumeAttributeClass applied to this PersistentVolumeClaim This is
     * a beta field and requires enabling VolumeAttributesClass feature (off by default).
     */
    var currentVolumeAttributesClassName: String? = null,
    /**
     * ModifyVolumeStatus represents the status object of ControllerModifyVolume operation.
     * When this is unset, there is no ModifyVolume operation being attempted. This is a beta
     * field and requires enabling VolumeAttributesClass feature (off by default).
     */
    val modifyVolumeStatus: ModifyVolumeStatus? = null,
    /**
     * phase represents the current phase of PersistentVolumeClaim.
     */
    val phase: String? = null
) : BaseStatus
