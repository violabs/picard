package io.violabs.picard.v2.resources.workload.autoscaling.metric

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ContainerResourceMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ExternalMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ObjectMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.PodsMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ResourceMetricSource

/**
 * MetricSpec specifies how to scale based on a single metric 
 * (only type and one other matching field should be set at once).
 */
@GeneratedDsl(withListGroup = true)
data class MetricSpec(
    /**
     * type is the type of metric source. It should be one of "ContainerResource",
     * "External", "Object", "Pods" or "Resource",
     * each mapping to a matching field in the object.
     */
    val type: MetricType,
    /**
     * containerResource refers to a resource metric (such as those specified
     * in requests and limits) known to Kubernetes describing a single container
     * in each pod of the current scale target (e.g. CPU or memory). Such metrics
     * are built in
     * to Kubernetes, and have special scaling options on top of those available to
     * normal per-pod metrics using the "pods" source.
     */
    val containerResource: ContainerResourceMetricSource? = null,
    /**
     * external refers to a global metric that is not associated with any Kubernetes object.
     * It allows autoscaling based on information coming from components running outside of
     * cluster (for example length of queue in cloud messaging service, or QPS from loadbalancer
     * running outside of cluster).
     */
    val external: ExternalMetricSource? = null,
    /**
     * object refers to a metric describing a single kubernetes object
     * (for example, hits-per-second on an Ingress object).
     */
    val objectSource: ObjectMetricSource? = null,
    /**
     * pods refers to a metric describing each pod in the current scale target (for example,
     * transactions-processed-per-second). The values will be averaged together before being
     * compared to the target value.
     */
    val pods: PodsMetricSource? = null,
    /**
     * resource refers to a resource metric (such as those specified in requests and limits)
     * known to Kubernetes describing each pod in the current scale target (e.g. CPU or memory).
     * Such metrics are built in to Kubernetes, and have special scaling options on top of those
     * available to normal per-pod metrics using the "pods" source.
     */
    val resource: ResourceMetricSource? = null
)