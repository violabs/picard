package io.violabs.picard.v2.resources.workload.autoscaling.metric.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerResourceMetricStatus indicates the current value of a resource
 * metric known to Kubernetes, as specified in requests and limits,
 * describing a single container in each pod in the current scale target
 * (e.g. CPU or memory). Such metrics are built in to Kubernetes,
 * and have special scaling options on top of those available to normal per-pod
 * metrics using the "pods" source.
 */
@GeneratedDsl
data class ContainerResourceMetricStatus(
    /**
     * container is the name of the container in the pods of the scaling target
     */
    val container: String,
    /**
     * current contains the current value for the given metric
     */
    val current: MetricValueStatus,
    /**
     * name is the name of the resource in question.
     */
    val name: String
)