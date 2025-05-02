package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricStatus
import io.violabs.picard.domain.DSLBuilder

data class ResourceMetricStatus(
    val current: MetricValueStatus,
    val name: String
) : BaseResourceMetricStatus {
    class Builder : DSLBuilder<ResourceMetricStatus> {
        private var current: MetricValueStatus? = null
        var name: String? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        override fun build(): ResourceMetricStatus {
            return ResourceMetricStatus(
                current = requireNotNull(current),
                name = requireNotNull(name)
            )
        }
    }
}