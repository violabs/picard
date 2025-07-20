package io.violabs.picard.domain.k8sResources.storage.csi.csiNode

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageListResource

data class CSINodeList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CSINode>,
    override val metadata: ListMeta? = null
) : StorageListResource<CSINodeList.Version, CSINode> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        CSINode,
        CSINode.Builder,
        CSINode.Group,
        CSINodeList
        >(CSINode.Group()) {

        override fun build(): CSINodeList {
            return CSINodeList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}