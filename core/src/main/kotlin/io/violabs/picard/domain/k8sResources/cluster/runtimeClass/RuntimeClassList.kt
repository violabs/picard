package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

data class RuntimeClassList(
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val items: List<RuntimeClass>,
    override val metadata: ListMeta? = null
) : ClusterListResource<RuntimeClassList.Version, RuntimeClass> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        RuntimeClass,
        RuntimeClass.Builder,
        RuntimeClass.Group,
        RuntimeClassList
        >(RuntimeClass.Group()) {

        override fun build(): RuntimeClassList {
            return RuntimeClassList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
