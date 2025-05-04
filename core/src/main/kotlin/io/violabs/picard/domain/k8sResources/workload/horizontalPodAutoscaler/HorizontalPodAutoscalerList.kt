package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class HorizontalPodAutoscalerList(
    override val apiVersion: Version = KAPIVersion.AutoscalingV2,
    override val items: List<HorizontalPodAutoscaler>,
    override val metadata: ListMeta? = null
) : K8sListResource<HorizontalPodAutoscalerList.Version, HorizontalPodAutoscaler> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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