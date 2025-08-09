package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.MetricStatus
import java.time.LocalDateTime

/**
 * HorizontalPodAutoscalerStatus describes the current status of a horizontal pod autoscaler.
 */
@GeneratedDsl
data class HorizontalPodAutoscalerStatus(
    /**
     * desiredReplicas is the desired number of replicas of pods managed by this autoscaler, as last calculated by the autoscaler.
     */
    val desiredReplicas: Int,
    /**
     * conditions is the set of conditions required for this autoscaler to scale its target, and indicates whether or not those conditions are met.
     */
    val conditions: List<HorizontalPodAutoscalerCondition>? = null,
    /**
     * currentMetrics is the last read state of the metrics used by this autoscaler.
     */
    val currentMetrics: List<MetricStatus>? = null,
    /**
     * currentReplicas is current number of replicas of pods managed by this autoscaler, as last seen by the autoscaler.
     */
    val currentReplicas: Int? = null,
    /**
     * lastScaleTime is the last time the HorizontalPodAutoscaler scaled the number of pods, used by the autoscaler to control how often the number of pods is changed.
     */
    val lastScaleTime: LocalDateTime? = null,
    /**
     * observedGeneration is the most recent generation observed by this autoscaler.
     */
    val observedGeneration: Long? = null
)