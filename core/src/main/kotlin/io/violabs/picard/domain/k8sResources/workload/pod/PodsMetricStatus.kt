package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.k8sResources.workload.Metric
import io.violabs.picard.domain.k8sResources.workload.pod.container.BaseContainer
import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerResourceMetricStatus

data class PodsMetricStatus(
    val current: Metric.ValueStatus,
    val metric: Metric.Identifier,
    val resource: ContainerResourceMetricStatus
)