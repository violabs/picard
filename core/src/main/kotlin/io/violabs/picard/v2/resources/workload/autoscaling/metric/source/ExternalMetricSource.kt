package io.violabs.picard.v2.resources.workload.autoscaling.metric.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTarget

/**
 * ExternalMetricSource indicates how to scale on a metric not associated with any Kubernetes object 
 * (for example length of queue in cloud messaging service, or QPS from loadbalancer running outside of cluster).
 */
@GeneratedDsl
data class ExternalMetricSource(
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier,
    /**
     * target specifies the target value for the given metric
     */
    val target: MetricTarget
)