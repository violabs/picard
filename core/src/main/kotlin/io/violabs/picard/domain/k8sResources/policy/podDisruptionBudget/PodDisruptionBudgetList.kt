package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyListResource

data class PodDisruptionBudgetList(
    override val apiVersion: Version = KAPIVersion.PolicyV1,
    override val items: List<PodDisruptionBudget>,
    override val metadata: ListMeta? = null
) : PolicyListResource<PodDisruptionBudgetList.Version, PodDisruptionBudget> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        PodDisruptionBudget,
        PodDisruptionBudget.Builder,
        PodDisruptionBudget.Group,
        PodDisruptionBudgetList
        >(PodDisruptionBudget.Group()) {

        override fun build(): PodDisruptionBudgetList {
            return PodDisruptionBudgetList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
