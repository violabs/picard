package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * MetricValueStatus holds the current value for a metric
 */
@GeneratedDsl
data class MetricValueStatus(
    /**
     * currentAverageUtilization is the current value of the average of the resource metric across all relevant pods,
     * represented as a percentage of the requested value of the resource for the pods.
     */
    val averageUtilization: Int? = null,
    /**
     * averageValue is the current value of the average of the metric across all relevant pods (as a quantity)
     */
    val averageValue: Quantity? = null,
    /**
     * value is the current value of the metric (as a quantity).
     */
    val value: Quantity? = null
)