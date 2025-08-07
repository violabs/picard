package io.violabs.picard.v2.resources.storage.volume.attachment

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/volume-attachment-v1/
 *
 * VolumeAttachment captures the intent to attach or detach the specified volume to/from the specified node.
 * apiVersion: storage.k8s.io/v1
 *
 * import "k8s.io/api/storage/v1"
 */
@GeneratedDsl(withListGroup = true)
data class VolumeAttachmentV2(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec represents specification of the desired attach/detach volume behavior.
     * Populated by the Kubernetes system.
     */
    val spec: VolumeAttachmentSpec? = null,
    /**
     * status represents status of the VolumeAttachment request. Populated by the
     * entity completing the attach or detach operation, i.e. the external-attacher.
     */
    val status: VolumeAttachmentStatus? = null
) : ConfigResource<VolumeAttachmentV2.Version, ObjectMeta> {
    interface Version : APIVersion
}