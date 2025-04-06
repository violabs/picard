package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class NodeList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Node>,
    override val metadata: ListMeta? = null
) : K8sListResource<NodeList.Version, Node> {
    interface Version : APIVersion
}
