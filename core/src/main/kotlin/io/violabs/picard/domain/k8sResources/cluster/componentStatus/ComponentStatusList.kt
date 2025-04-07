package io.violabs.picard.domain.k8sResources.cluster.componentStatus

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ComponentStatusList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ComponentStatus>,
    override val metadata: ListMeta? = null
) : K8sListResource<ComponentStatusList.Version, ComponentStatus> {
    interface Version : APIVersion
}
