package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * NodeList is a list of Nodes.
 */
@GeneratedDsl
data class NodeList(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Node>,
    override val metadata: ListMeta? = null
) : ClusterListResource<NodeList.Version, Node> {
    interface Version : APIVersion
}
