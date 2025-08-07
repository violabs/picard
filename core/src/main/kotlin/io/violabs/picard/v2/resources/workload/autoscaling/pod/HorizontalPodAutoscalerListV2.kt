package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class HorizontalPodAutoscalerListV2(
    @DefaultValue(
        "KAPIVersion.AutoscalingV2",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AutoscalingV2,
    override val items: List<HorizontalPodAutoscalerV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<HorizontalPodAutoscalerListV2.Version, HorizontalPodAutoscalerV2> {
    interface Version : APIVersion
}
