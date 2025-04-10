package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.BaseResourceMetricStatus
import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.Metric

data class ContainerResourceMetricStatus(
    val container: String,
    val current: Metric.ValueStatus,
    val name: String
) : BaseResourceMetricStatus {
    class Builder : DslBuilder<ContainerResourceMetricStatus> {
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