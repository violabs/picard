package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricSource
import io.violabs.picard.domain.DSLBuilder

data class ContainerResourceMetricSource(
    val container: String,
    val name: String,
    val target: MetricTarget? = null
) : BaseResourceMetricSource {
    class Builder : DSLBuilder<ContainerResourceMetricSource> {
        var container: String? = null
        var name: String? = null
        private var target: MetricTarget? = null

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): ContainerResourceMetricSource {
            return ContainerResourceMetricSource(
                container = container ?: error("container is required"),
                name = name ?: error("name is required"),
                target = target
            )
        }
    }
}