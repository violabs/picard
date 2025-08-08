package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.PolicyListResource

@GeneratedDsl
data class PriorityLevelConfigurationList(
    @DefaultValue(
        "KAPIVersion.FlowControlApiServerV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val items: List<PriorityLevelConfiguration>,
    override val metadata: ListMeta? = null
) : PolicyListResource<PriorityLevelConfigurationList.Version, PriorityLevelConfiguration> {
    interface Version : APIVersion
}
