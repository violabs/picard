package io.violabs.picard.domain.k8sResources.storage.csi.csiNode

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

class CSINodeList(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val items: List<CSINode>,
    override val metadata: ListMeta? = null
) : K8sListResource<CSINodeList.Version, CSINode> {
    interface Version : APIVersion
}