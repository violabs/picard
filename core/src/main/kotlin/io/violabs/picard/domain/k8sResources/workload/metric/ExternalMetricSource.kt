package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class ExternalMetricSource(
    val metric: MetricIdentifier,
    val target: MetricTarget
) {
    class Builder : DslBuilder<ExternalMetricSource> {
        private var metric: MetricIdentifier? = null
        private var target: MetricTarget? = null

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): ExternalMetricSource {
            return ExternalMetricSource(
                metric = vRequireNotNull(this::metric),
                target = vRequireNotNull(this::target)
            )
        }
    }
}