package io.violabs.picard.domain.k8sResources.workload

class ResourceMetricStatus(
    override val current: Metric.ValueStatus,
    override val name: String
) : BaseResourceMetricStatus