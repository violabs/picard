package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseResourceMetricSource
import io.violabs.picard.common.DslBuilder

data class ResourceMetricSource(
    val name: String,
    val target: MetricTarget? = null
) : BaseResourceMetricSource {
    class Builder : DslBuilder<ResourceMetricSource> {
        var name: String? = null
        private var target: MetricTarget? = null

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): ResourceMetricSource {
            return ResourceMetricSource(
                name = vRequireNotNull(this::name),
                target = target
            )
        }
    }
}