package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseResourceMetricStatus
import io.violabs.picard.domain.DSLBuilder

data class ContainerResourceMetricStatus(
    val container: String,
    val current: MetricValueStatus,
    val name: String
) : BaseResourceMetricStatus {
    class Builder : DSLBuilder<ContainerResourceMetricStatus> {
        var container: String? = null
        private var current: MetricValueStatus? = null
        var name: String? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        override fun build(): ContainerResourceMetricStatus {
            return ContainerResourceMetricStatus(
                container = vRequireNotNull(this::container),
                current = vRequireNotNull(this::current),
                name = vRequireNotNull(this::name)
            )
        }
    }
}