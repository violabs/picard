package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseResourceMetricStatus
import io.violabs.picard.common.DslBuilder

data class ResourceMetricStatus(
    val current: MetricValueStatus,
    val name: String
) : BaseResourceMetricStatus {
    class Builder : DslBuilder<ResourceMetricStatus> {
        private var current: MetricValueStatus? = null
        var name: String? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        override fun build(): ResourceMetricStatus {
            return ResourceMetricStatus(
                current = vRequireNotNull(this::current),
                name = vRequireNotNull(this::name)
            )
        }
    }
}