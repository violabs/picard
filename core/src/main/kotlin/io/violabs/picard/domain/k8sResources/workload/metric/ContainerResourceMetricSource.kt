package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
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
                container = vRequireNotNull(this::container),
                name = vRequireNotNull(this::name),
                target = target
            )
        }
    }
}