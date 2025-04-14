package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference

data class ObjectMetricStatus(
    val current: Metric.ValueStatus,
    val describedObject: CrossVersionObjectReference,
    val metric: Metric.Identifier,
    val pods: PodsMetricSource
)