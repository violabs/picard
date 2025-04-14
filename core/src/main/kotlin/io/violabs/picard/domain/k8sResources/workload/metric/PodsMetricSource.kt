package io.violabs.picard.domain.k8sResources.workload.metric

data class PodsMetricSource(
    val metric: Metric.Identifier,
    val target: Metric.Target
)