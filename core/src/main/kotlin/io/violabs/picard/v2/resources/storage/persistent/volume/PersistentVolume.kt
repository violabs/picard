package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * PersistentVolume (PV) is a storage resource provisioned by an administrator.
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-v1/
 *
 * PersistentVolume (PV) is a storage resource provisioned by an administrator.
 * It is analogous to a node. More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes
 */
@GeneratedDsl(withListGroup = true)
data class PersistentVolume(
    @DefaultValue(
        "KAPIVersion.V1",
        packageName = "io.violabs.picard.domain.k8sResources",
        className = "KAPIVersion"
    )
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata. More info:
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    val spec: PersistentVolumeSpec? = null,
    val status: PersistentVolumeStatus? = null,
) : StorageResource<PersistentVolume.Version, ObjectMeta> {
    interface Version : APIVersion
}