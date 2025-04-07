package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BaseResourceMetricStatus

class ResourceMetricStatus(
    override val current: Metric.ValueStatus,
    override val name: String
) : BaseResourceMetricStatus