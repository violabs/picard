package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/workload-resources/horizontal-pod-autoscaler-v2/
 *
 * HorizontalPodAutoscaler is the configuration for a horizontal pod autoscaler,
 * which automatically manages the replica count
 * of any resource implementing the scale subresource based on the metrics specified.
 *
 * apiVersion: autoscaling/v2
 *
 * import "k8s.io/api/autoscaling/v2"
 */
@GeneratedDsl(withListGroup = true)
data class HorizontalPodAutoscalerV2(
    @DefaultValue(
        "KAPIVersion.AutoscalingV2",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AutoscalingV2,
    /**
     * metadata is the standard object metadata. 
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the specification for the behaviour of the autoscaler. 
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status.
     */
    val spec: HorizontalPodAutoscalerSpec? = null,
    /**
     * status is the current information about the autoscaler.
     */
    val status: HorizontalPodAutoscalerStatus? = null
) : WorkloadResource<HorizontalPodAutoscalerV2.Version, ObjectMeta> {
    interface Version : APIVersion
}