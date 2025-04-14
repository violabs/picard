package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricSource
import io.violabs.picard.domain.DslBuilder

data class ContainerResourceMetricSource(
    val container: String,
    val name: String,
    val target: Metric.Target? = null
) : BaseResourceMetricSource {
    class Builder : DslBuilder<ContainerResourceMetricSource> {
        private var container: String? = null
        private var name: String? = null
        private var target: Metric.Target? = null

        override fun build(): ContainerResourceMetricSource {
            return ContainerResourceMetricSource(
                container = container ?: error("container is required"),
                name = name ?: error("name is required"),
                target = target
            )
        }
    }
}