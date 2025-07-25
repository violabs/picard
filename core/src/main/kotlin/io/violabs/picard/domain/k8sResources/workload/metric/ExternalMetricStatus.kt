package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class ExternalMetricStatus(
    val current: MetricValueStatus,
    val metric: MetricIdentifier
) {
    class Builder : DslBuilder<ExternalMetricStatus> {
        private var current: MetricValueStatus? = null
        private var metric: MetricIdentifier? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        override fun build(): ExternalMetricStatus {
            return ExternalMetricStatus(
                current = vRequireNotNull(this::current),
                metric = vRequireNotNull(this::metric)
            )
        }
    }
}