package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier

/**
 * ExternalMetricStatus indicates the current value of a global metric not associated with any Kubernetes object.
 */
@GeneratedDsl
data class ExternalMetricStatus(
    /**
     * current contains the current value for the given metric
     */
    val current: MetricValueStatus,
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier
)