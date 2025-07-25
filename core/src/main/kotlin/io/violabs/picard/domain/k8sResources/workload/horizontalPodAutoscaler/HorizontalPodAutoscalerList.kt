package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class HorizontalPodAutoscalerList(
    override val apiVersion: Version = KAPIVersion.AutoscalingV2,
    override val items: List<HorizontalPodAutoscaler>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<HorizontalPodAutoscalerList.Version, HorizontalPodAutoscaler> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        HorizontalPodAutoscaler,
        HorizontalPodAutoscaler.Builder,
        HorizontalPodAutoscaler.Group,
        HorizontalPodAutoscalerList>(HorizontalPodAutoscaler.Group()) {

        override fun build(): HorizontalPodAutoscalerList {
            return HorizontalPodAutoscalerList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}