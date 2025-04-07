package io.violabs.picard.domain.k8sResources.workload

data class ObjectMetricSource(
    val describedObject: CrossVersionObjectReference,
    val metric: Metric.Identifier,
    val target: Metric.Target
)