package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BaseResourceMetricSource

data class ResourceMetricSource(
    override val name: String,
    override val target: Metric.Target? = null
) : BaseResourceMetricSource