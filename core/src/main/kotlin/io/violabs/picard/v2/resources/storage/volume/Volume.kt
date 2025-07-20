package io.violabs.picard.v2.resources.configstorage.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.configstorage.volume.source.ConfigMapVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.CsiVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.DownwardApiVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.EmptyDirVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.EphemeralVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.FcVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.HostPathVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.ImageVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.IscsiVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.NfsVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.PersistentVolumeClaimVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.ProjectedVolumeSource
import io.violabs.picard.v2.resources.configstorage.volume.source.SecretVolumeSource

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/volume/
 *
 * deprecated not included volumes (AWSElasticBlockStore, AzureDisk, AzureFile, CephFS, FC)
 */
@GeneratedDsl(withListGroup = true)
data class Volume(
    /**
     * Name of the volume. Must be a DNS_LABEL and unique within the pod.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String,
    //region exposed persistent volumes
    /**
     * Reference to a PersistentVolumeClaim in the same namespace.
     */
    val persistentVolumeClaim: PersistentVolumeClaimVolumeSource? = null,
    //endregion

    //region projections
    /**
     * Represents a configMap that should populate this volume.
     */
    val configMap: ConfigMapVolumeSource? = null,
    /**
     * Represents a configMap that should populate this volume.
     */
    val secret: SecretVolumeSource? = null,
    /**
     * Represents downward API about the pod that should populate this volume
     */
    val downwardApi: DownwardApiVolumeSource? = null,
    /**
     * Projected items for all in one resources secrets, configmaps, and downward API
     *
     * Represents a projected volume source
     */
    val projected: ProjectedVolumeSource? = null,
    //endregion

    //region local / temporary directory
    /**
     * Represents an empty directory for a pod. Empty directory volumes support ownership management
     * and SELinux relabeling.
     */
    val emptyDir: EmptyDirVolumeSource? = null,
    /**
     * Represents a host path mapped into a pod. Host path volumes do not support
     * ownership management or SELinux relabeling.
     */
    val hostPath: HostPathVolumeSource? = null,
    //endregion

    //region persistent volumes
    /**
     * Represents a source location of a volume to mount, managed by an external CSI driver
     */
    val csi: CsiVolumeSource? = null,
    /**
     * Represents an ephemeral volume that is handled by a normal storage driver.
     */
    val ephemeral: EphemeralVolumeSource? = null,
    /**
     * fc represents a Fibre Channel resource that is attached to a kubelet's host machine and then
     * exposed to the pod.
     */
    val fc: FcVolumeSource? = null,
    /**
     * iscsi represents an ISCSI Disk resource that is attached to a kubelet's host machine and then
     * exposed to the pod. More info: https://examples.k8s.io/volumes/iscsi/README.md
     */
    val iscsi: IscsiVolumeSource? = null,
    /**
     * image represents an OCI object (a container image or artifact) pulled and mounted on the
     * kubelet's host machine. The volume is resolved at pod startup depending on which PullPolicy
     * value is provided:
     *
     * Always: the kubelet always attempts to pull the reference. Container creation will fail If
     * the pull fails. - Never: the kubelet never pulls the reference and only uses a local image
     * or artifact. Container creation will fail if the reference isn't present. - IfNotPresent:
     * the kubelet pulls if the reference isn't already present on disk. Container creation will fail
     * if the reference isn't present and the pull fails.
     *
     * The volume gets re-resolved if the pod gets deleted and recreated, which means that new remote
     * content will become available on pod recreation. A failure to resolve or pull the image during
     * pod startup will block containers from starting and may add significant latency. Failures will
     * be retried using normal volume backoff and will be reported on the pod reason and message. The
     * types of objects that may be mounted by this volume are defined by the container runtime
     * implementation on a host machine and at minimum must include all valid types supported by the
     * container image field. The OCI object gets mounted in a single directory
     * (spec.containers[].volumeMounts.mountPath) by merging the manifest layers in the same way as
     * for container images. The volume will be mounted read-only (ro) and non-executable
     * files (noexec). Sub path mounts for containers are not supported
     * (spec.containers[].volumeMounts.subpath) before 1.33. The field
     * spec.securityContext.fsGroupChangePolicy has no effect on this volume type.
     */
    val image: ImageVolumeSource? = null,
    /**
     * nfs represents an NFS mount on the host that shares a pod's lifetime
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs
     */
    val nfs: NfsVolumeSource? = null,
    //endregion
)