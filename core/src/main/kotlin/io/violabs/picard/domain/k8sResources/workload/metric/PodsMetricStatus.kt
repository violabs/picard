package io.violabs.picard.domain.k8sResources.workload.metric

data class PodsMetricStatus(
    val current: Metric.ValueStatus,
    val metric: Metric.Identifier,
    val resource: ContainerResourceMetricStatus
)