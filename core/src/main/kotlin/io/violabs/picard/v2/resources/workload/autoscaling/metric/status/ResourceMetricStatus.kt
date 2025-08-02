package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceMetricStatus indicates the current value of a resource metric known to
 * Kubernetes, as specified in requests and limits, describing each pod in the current
 * scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special
 * scaling options on top of those available to normal per-pod metrics using the "pods" source.
 */
@GeneratedDsl
data class ResourceMetricStatus(
    /**
     * current contains the current value for the given metric
     */
    val current: MetricValueStatus,
    /**
     * name is the name of the resource in question.
     */
    val name: String
)