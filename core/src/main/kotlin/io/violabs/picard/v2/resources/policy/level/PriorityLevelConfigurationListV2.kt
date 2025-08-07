package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PriorityLevelConfigurationListV2(
    @DefaultValue(
        "KAPIVersion.FlowControlApiServerV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val items: List<PriorityLevelConfigurationV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<PriorityLevelConfigurationListV2.Version, PriorityLevelConfigurationV2> {
    interface Version : APIVersion
}
