package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference

data class ObjectMetricSource(
    val describedObject: CrossVersionObjectReference,
    val metric: Metric.Identifier,
    val target: Metric.Target
)