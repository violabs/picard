package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReference
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier

/**
 * ObjectMetricStatus indicates the current value of a metric describing a kubernetes object 
 * (for example, hits-per-second on an Ingress object).
 */
@GeneratedDsl
data class ObjectMetricStatus(
    /**
     * current contains the current value for the given metric
     */
    val current: MetricValueStatus,
    /**
     * DescribedObject specifies the descriptions of a object,such as kind,name apiVersion
     */
    val describedObject: CrossVersionObjectReference,
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier
)