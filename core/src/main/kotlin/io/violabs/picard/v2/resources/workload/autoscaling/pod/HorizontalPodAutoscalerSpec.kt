package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReference
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricSpec

/**
 * HorizontalPodAutoscalerSpec describes the desired functionality of the HorizontalPodAutoscaler.
 */
@GeneratedDsl
data class HorizontalPodAutoscalerSpec(
    /**
     * minReplicas is the lower limit for the number of replicas to which the autoscaler can scale down.
     * It defaults to 1 pod. minReplicas is allowed to be 0 if the alpha feature gate HPAScaleToZero is enabled
     * and at least one Object or External metric is configured. Scaling is active as long as at least
     * one metric value is available.
     */
    val minReplicas: Int? = null,
    /**
     * maxReplicas is the upper limit for the number of replicas to which the autoscaler can scale up. 
     * It cannot be less that minReplicas.
     */
    val maxReplicas: Int,
    /**
     * scaleTargetRef points to the target resource to scale, and is used to the pods for
     * which metrics should be collected, as well as to actually change the replica count.
     */
    val scaleTargetRef: CrossVersionObjectReference,
    /**
     * metrics contains the specifications for which to use to calculate the desired replica count
     * (the maximum replica count across all metrics will be used).
     * The desired replica count is calculated multiplying the ratio between the target
     * value and the current value by the current number of pods.
     * Ergo, metrics used must decrease as the pod count is increased, and vice-versa.
     * See the individual metric source types for more information about how each type of metric must respond.
     * If not set, the default metric will be set to 80% average CPU utilization.
     */
    val metrics: List<MetricSpec>? = null,
    /**
     * behavior configures the scaling behavior of the target in both Up and Down directions (scaleUp and scaleDown fields respectively). 
     * If not set, the default HPAScalingRules for scale up and scale down are used.
     */
    val behavior: HorizontalPodAutoscalerBehavior? = null,
)