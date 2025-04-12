package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.k8sResources.workload.pod.metric.PodsMetricSource

data class ObjectMetricStatus(
    val current: Metric.ValueStatus,
    val describedObject: CrossVersionObjectReference,
    val metric: Metric.Identifier,
    val pods: PodsMetricSource
)