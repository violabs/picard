package io.violabs.picard.domain.k8sResources.cluster.namespace

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.cluster.namespace.NamespaceListV2"))
data class NamespaceList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Namespace>,
    override val metadata: ListMeta? = null
) : ClusterListResource<NamespaceList.Version, Namespace> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        Namespace,
        Namespace.Builder,
        Namespace.Group,
        NamespaceList
        >(Namespace.Group()) {

        override fun build(): NamespaceList {
            return NamespaceList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
