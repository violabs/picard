package io.violabs.picard.domain.k8sResources.storage.volumeAttachment

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class VolumeAttachmentList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<VolumeAttachment>,
    override val metadata: ListMeta? = null
) : StorageListResource<VolumeAttachmentList.Version, VolumeAttachment> {
    interface Version : APIVersion
    class Builder : ResourceListDslBuilder<
        VolumeAttachment,
        VolumeAttachment.Builder,
        VolumeAttachment.Group,
        VolumeAttachmentList>(VolumeAttachment.Group()) {

        override fun build(): VolumeAttachmentList {
            return VolumeAttachmentList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
