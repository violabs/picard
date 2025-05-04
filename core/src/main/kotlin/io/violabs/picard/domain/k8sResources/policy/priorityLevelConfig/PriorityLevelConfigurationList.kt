package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyListResource

data class PriorityLevelConfigurationList(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val items: List<PriorityLevelConfiguration>,
    override val metadata: ListMeta? = null
) : PolicyListResource<PriorityLevelConfigurationList.Version, PriorityLevelConfiguration> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        PriorityLevelConfiguration,
        PriorityLevelConfiguration.Builder,
        PriorityLevelConfiguration.Group,
        PriorityLevelConfigurationList
        >(PriorityLevelConfiguration.Group()) {

        override fun build(): PriorityLevelConfigurationList {
            return PriorityLevelConfigurationList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
