package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BaseResourceMetricStatus
import io.violabs.picard.domain.DSLBuilder

data class ContainerResourceMetricStatus(
    val container: String,
    val current: Metric.ValueStatus,
    val name: String
) : BaseResourceMetricStatus {
    class Builder : DSLBuilder<ContainerResourceMetricStatus> {
        private var container: String? = null
        private var current: Metric.ValueStatus? = null
        private var name: String? = null

        override fun build(): ContainerResourceMetricStatus {
            return ContainerResourceMetricStatus(
                container = container ?: error("container is required"),
                current = current ?: error("current is required"),
                name = name ?: error("name is required")
            )
        }
    }
}