package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.v2.common.ObjectReference
import io.violabs.picard.v2.resources.storage.persistent.volume.source.CsiPersistentVolumeSource
import io.violabs.picard.v2.resources.storage.persistent.volume.source.IscsiPersistentVolumeSource
import io.violabs.picard.v2.resources.storage.volume.source.FcVolumeSource
import io.violabs.picard.v2.resources.storage.volume.source.HostPathVolumeSource
import io.violabs.picard.v2.resources.storage.volume.source.LocalVolumeSource
import io.violabs.picard.v2.resources.storage.volume.source.NfsVolumeSource

/**
 * PersistentVolumeSpec is the specification of a persistent volume.
 */
@GeneratedDsl
data class PersistentVolumeSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * accessModes contains all ways the volume can be mounted.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#access-modes
     */
    val accessModes: List<String>? = null,
    /**
     * capacity is the description of the persistent volume's resources and capacity.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#capacity
     */
    val capacity: Map<String, Quantity>? = null,
    /**
     * claimRef is part of a bi-directional binding between PersistentVolume and
     * PersistentVolumeClaim. Expected to be non-nil when bound. claim.VolumeName is the
     * authoritative bind between PV and PVC.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#binding
     */
    val claimRef: ObjectReference? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * mountOptions is the list of mount options, e.g. ["ro", "soft"]. Not validated - mount
     * will simply fail if one is invalid. More
     * info: https://kubernetes.io/docs/concepts/storage/persistent-volumes/#mount-options
     */
    val mountOptions: List<String>? = null,
    /**
     * nodeAffinity defines constraints that limit what nodes this volume can be accessed from.
     * This field influences the scheduling of pods that use this volume.
     *
     * VolumeNodeAffinity defines constraints that limit what nodes this volume can be
     * accessed from.
     */
    val nodeAffinity: VolumeNodeAffinity? = null,
    /**
     * persistentVolumeReclaimPolicy defines what happens to a persistent volume when released
     * from its claim. Valid options are Retain (default for manually created PersistentVolumes),
     * Delete (default for dynamically provisioned PersistentVolumes), and Recycle (deprecated).
     * Recycle must be supported by the volume plugin underlying this PersistentVolume.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#reclaiming
     */
    val persistentVolumeReclaimPolicy: PersistentVolumeReclaimPolicy? = null,
    /**
     * storageClassName is the name of StorageClass to which this persistent volume belongs.
     * Empty value means that this volume does not belong to any StorageClass.
     */
    val storageClassName: String? = null,
    /**
     * Name of VolumeAttributesClass to which this persistent volume belongs. Empty value is not
     * allowed. When this field is not set, it indicates that this volume does not belong to any
     * VolumeAttributesClass. This field is mutable and can be changed by the CSI driver after a
     * volume has been updated successfully to a new class. For an unbound PersistentVolume, the
     * volumeAttributesClassName will be matched with unbound PersistentVolumeClaims during the
     * binding process. This is a beta field and requires enabling VolumeAttributesClass
     * feature (off by default).
     */
    val volumeAttributesClassName: String? = null,
    /**
     * volumeMode defines if a volume is intended to be used with a formatted filesystem or to
     * remain in raw block state. Value of Filesystem is implied when not included in spec.
     */
    val volumeMode: String? = null,
    //region Local
    /**
     * hostPath represents a directory on the host. Provisioned by a developer or tester. This
     * is useful for single-node development and testing only! On-host storage is not supported
     * in any way and WILL NOT WORK in a multi-node cluster.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#hostpath
     *
     * Represents a host path mapped into a pod. Host path volumes do not support ownership
     * management or SELinux relabeling.
     */
    val hostPath: HostPathVolumeSource? = null,
    /**
     * local represents directly-attached storage with node affinity
     *
     * Local represents directly-attached storage with node affinity
     */
    val local: LocalVolumeSource? = null,
    //endregion
    //region Providers
    /**
     * csi represents storage that is handled by an external CSI driver.
     *
     * Represents storage that is managed by an external CSI volume driver
     */
    val csi: CsiPersistentVolumeSource? = null,
    /**
     * fc represents a Fibre Channel resource that is attached to a kubelet's host
     * machine and then exposed to the pod.
     */
    val fc: FcVolumeSource? = null,
    /**
     * iscsi represents an ISCSI Disk resource that is attached to a kubelet's host machine
     * and then exposed to the pod. Provisioned by an admin.
     */
    val iscsi: IscsiPersistentVolumeSource? = null,
    /**
     * nfs represents an NFS mount on the host. Provisioned by an admin.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs
     */
    val nfs: NfsVolumeSource? = null,
    //endregion
) : BaseSpec