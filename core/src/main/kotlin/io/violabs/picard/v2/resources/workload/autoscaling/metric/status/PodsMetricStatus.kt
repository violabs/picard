package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier

/**
 * PodsMetricStatus indicates the current value of a metric describing each pod in the current scale target 
 * (for example, transactions-processed-per-second).
 */
@GeneratedDsl
data class PodsMetricStatus(
    /**
     * current contains the current value for the given metric
     */
    val current: MetricValueStatus,
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier
)