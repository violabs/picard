package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.k8sResources.workload.Metric

data class PodsMetricSource(
    val metric: Metric.Identifier,
    val target: Metric.Target
)