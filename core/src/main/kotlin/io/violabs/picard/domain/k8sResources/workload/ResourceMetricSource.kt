package io.violabs.picard.domain.k8sResources.workload

data class ResourceMetricSource(
    override val name: String,
    override val target: Metric.Target? = null
) : BaseResourceMetricSource