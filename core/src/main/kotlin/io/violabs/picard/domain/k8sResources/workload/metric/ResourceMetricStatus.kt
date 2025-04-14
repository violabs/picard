package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricStatus

class ResourceMetricStatus(
    val current: Metric.ValueStatus,
    val name: String
) : BaseResourceMetricStatus