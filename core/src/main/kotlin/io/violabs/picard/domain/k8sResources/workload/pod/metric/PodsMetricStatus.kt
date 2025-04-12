package io.violabs.picard.domain.k8sResources.workload.pod.metric

import io.violabs.picard.domain.k8sResources.workload.Metric
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerResourceMetricStatus

data class PodsMetricStatus(
    val current: Metric.ValueStatus,
    val metric: Metric.Identifier,
    val resource: ContainerResourceMetricStatus
)