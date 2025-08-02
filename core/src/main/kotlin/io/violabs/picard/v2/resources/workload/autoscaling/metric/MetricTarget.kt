package io.violabs.picard.v2.resources.workload.autoscaling.metric

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity

/**
 * MetricTarget defines the target value, average value, or average utilization of a specific metric
 */
@GeneratedDsl
data class MetricTarget(
    /**
     * type represents whether the metric type is Utilization, Value, or AverageValue
     */
    val type: Type,
    /**
     * averageUtilization is the target value of the average of the resource metric across all relevant pods, 
     * represented as a percentage of the requested value of the resource for the pods. 
     * Currently only valid for Resource metric source type
     */
    val averageUtilization: Int? = null,
    /**
     * averageValue is the target value of the average of the metric across all relevant pods (as a quantity)
     */
    val averageValue: Quantity? = null,
    /**
     * value is the target value of the metric (as a quantity).
     */
    val value: Quantity? = null
) {
    enum class Type {
        /**
         * Utilization represents the target value as a percentage of the requested value of the resource for the pods
         */
        Utilization,

        /**
         * Value represents the target value as a specific quantity
         */
        Value,

        /**
         * AverageValue represents the target value as an average across all relevant pods
         */
        AverageValue
    }
}