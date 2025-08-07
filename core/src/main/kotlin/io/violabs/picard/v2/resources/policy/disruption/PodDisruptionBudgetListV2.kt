package io.violabs.picard.v2.resources.policy.disruption

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PodDisruptionBudgetListV2(
    @DefaultValue(
        "KAPIVersion.PolicyV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.PolicyV1,
    override val items: List<PodDisruptionBudgetV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodDisruptionBudgetListV2.Version, PodDisruptionBudgetV2> {
    interface Version : APIVersion
}
