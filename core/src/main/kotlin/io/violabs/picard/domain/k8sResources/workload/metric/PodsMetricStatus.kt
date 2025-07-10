package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class PodsMetricStatus(
    val current: MetricValueStatus,
    val metric: MetricIdentifier,
    val resource: ContainerResourceMetricStatus
) {
    class Builder : DslBuilder<PodsMetricStatus> {
        private var current: MetricValueStatus? = null
        private var metric: MetricIdentifier? = null
        private var resource: ContainerResourceMetricStatus? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        fun resource(block: ContainerResourceMetricStatus.Builder.() -> Unit) {
            resource = ContainerResourceMetricStatus.Builder().apply(block).build()
        }

        override fun build(): PodsMetricStatus {
            return PodsMetricStatus(
                current = vRequireNotNull(this::current),
                metric = vRequireNotNull(this::metric),
                resource = vRequireNotNull(this::resource)
            )
        }
    }
}