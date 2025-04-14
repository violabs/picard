package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricSource

data class ResourceMetricSource(
    val name: String,
    val target: Metric.Target? = null
) : BaseResourceMetricSource