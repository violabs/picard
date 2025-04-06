package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PriorityLevelConfigurationList(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val items: List<PriorityLevelConfiguration>,
    override val metadata: ListMeta? = null
) : K8sListResource<PriorityLevelConfigurationList.Version, PriorityLevelConfiguration> {
    interface Version : APIVersion
}
