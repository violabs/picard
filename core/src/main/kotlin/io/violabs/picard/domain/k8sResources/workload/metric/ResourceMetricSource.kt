package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricSource
import io.violabs.picard.domain.DSLBuilder

data class ResourceMetricSource(
    val name: String,
    val target: MetricTarget? = null
) : BaseResourceMetricSource {
    class Builder : DSLBuilder<ResourceMetricSource> {
        var name: String? = null
        private var target: MetricTarget? = null

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): ResourceMetricSource {
            return ResourceMetricSource(
                name = requireNotNull(name),
                target = target
            )
        }
    }
}