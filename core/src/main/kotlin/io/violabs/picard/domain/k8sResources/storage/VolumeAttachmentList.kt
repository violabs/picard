package io.violabs.picard.domain.k8sResources.storage

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class VolumeAttachmentList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<VolumeAttachment>,
    override val metadata: ListMeta? = null
) : K8sListResource<VolumeAttachmentList.Version, VolumeAttachment> {
    interface Version : APIVersion
}
