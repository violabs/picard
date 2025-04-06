package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PodDisruptionBudgetList(
    override val apiVersion: Version = KAPIVersion.PolicyV1,
    override val items: List<PodDisruptionBudget>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodDisruptionBudgetList.Version, PodDisruptionBudget> {
    interface Version : APIVersion
}
