package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.k8sResources.workload.Metric
import io.violabs.picard.domain.k8sResources.workload.pod.container.BaseContainer

data class PodsMetricStatus(
    val current: Metric.ValueStatus,
    val metric: Metric.Identifier,
    val resource: BaseContainer.ResourceMetricStatus
)