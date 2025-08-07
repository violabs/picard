package io.violabs.picard.v2.resources.storage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/persistent-volume-claim-v1/
 *
 * import "k8s.io/api/core/v1"
 */
@GeneratedDsl(withListGroup = true)
data class PersistentVolumeClaim(
    @DefaultValue(
        "KAPIVersion.V1",
        packageName = "io.violabs.picard.domain.k8sResources",
        className = "KAPIVersion"
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    val spec: PersistentVolumeClaimSpec? = null,
    val status: PersistentVolumeClaimStatus? = null
) : StorageResource<PersistentVolumeClaim.Version, ObjectMeta> {
    interface Version : APIVersion
}