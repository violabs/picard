package io.violabs.picard.v2.resources.workload.autoscaling.metric.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReference
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTarget

/**
 * ObjectMetricSource indicates how to scale on a metric describing a kubernetes object 
 * (for example, hits-per-second on an Ingress object).
 */
@GeneratedDsl
data class ObjectMetricSource(
    /**
     * describedObject specifies the descriptions of a object,such as kind,name apiVersion
     */
    val describedObject: CrossVersionObjectReference,
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier,
    /**
     * target specifies the target value for the given metric
     */
    val target: MetricTarget
)