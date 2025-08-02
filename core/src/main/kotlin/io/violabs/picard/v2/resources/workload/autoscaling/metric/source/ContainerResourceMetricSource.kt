package io.violabs.picard.v2.resources.workload.autoscaling.metric.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTarget

/**
 * ContainerResourceMetricSource indicates how to scale on a resource metric
 * known to Kubernetes, as specified in requests and limits,
 * describing each pod in the current scale target (e.g. CPU or memory). The
 * values will be averaged together before being compared
 * to the target. Such metrics are built in to Kubernetes, and have special scaling
 * options on top of those available to normal
 * per-pod metrics using the "pods" source. Only one "target" type should be set.
 */
@GeneratedDsl
data class ContainerResourceMetricSource(
    /**
     * container is the name of the container in the pods of the scaling target
     */
    val container: String,
    /**
     * name is the name of the resource in question.
     */
    val name: String,
    /**
     * target specifies the target value for the given metric
     */
    val target: MetricTarget
)