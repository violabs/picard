package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class PodsMetricSource(
    val metric: MetricIdentifier,
    val target: MetricTarget
) {
    class Builder : DSLBuilder<PodsMetricSource> {
        private var metric: MetricIdentifier? = null
        private var target: MetricTarget? = null

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): PodsMetricSource {
            return PodsMetricSource(
                metric = vRequireNotNull(this::metric),
                target = vRequireNotNull(this::target)
            )
        }
    }
}