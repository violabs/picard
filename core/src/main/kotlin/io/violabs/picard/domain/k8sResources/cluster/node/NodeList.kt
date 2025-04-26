package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class NodeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Node>,
    override val metadata: ListMeta? = null
) : K8sListResource<NodeList.Version, Node> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Node,
        Node.Builder,
        Node.Group,
        NodeList
        >(Node.Group()) {

        override fun build(): NodeList {
            return NodeList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
