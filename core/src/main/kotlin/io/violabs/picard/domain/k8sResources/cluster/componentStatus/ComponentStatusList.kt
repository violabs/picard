package io.violabs.picard.domain.k8sResources.cluster.componentStatus

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ComponentStatusList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ComponentStatus>,
    override val metadata: ListMeta? = null
) : K8sListResource<ComponentStatusList.Version, ComponentStatus> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ComponentStatus,
        ComponentStatus.Builder,
        ComponentStatus.Group,
        ComponentStatusList
        >(ComponentStatus.Group()) {

        override fun build(): ComponentStatusList {
            return ComponentStatusList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
