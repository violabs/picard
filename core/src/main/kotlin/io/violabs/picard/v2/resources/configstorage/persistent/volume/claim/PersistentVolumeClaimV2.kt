package io.violabs.picard.v2.resources.configstorage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.v2.common.LabelSelector
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.framework.section.StorageResource

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
@GeneratedDsl
data class PersistentVolumeClaimV2(
    val apiVersion: Version = KAPIVersion.V1,
    val metadata: ObjectMeta? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : StorageResource {
    interface Version : APIVersion

    /**
     * PersistentVolumeClaimSpec describes the common attributes of storage devices and allows a
     * Source for provider-specific attributes
     */
    @GeneratedDsl
    data class Spec(
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

    /**
     * PersistentVolumeClaimStatus is the current status of a persistent volume claim.
     */
    @GeneratedDsl
    data class Status(
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
}