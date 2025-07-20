package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class MetricStatus(
    val type: MetricType,
    val containerResource: ContainerResourceMetricStatus? = null,
    val external: ExternalMetricStatus? = null,
    val objectMetricStatus: ObjectMetricStatus? = null,
    val pods: PodsMetricStatus? = null,
    val resource: ResourceMetricStatus? = null
) {
    class Builder : DslBuilder<MetricStatus> {
        var type: MetricType? = null
        private var containerResource: ContainerResourceMetricStatus? = null
        private var external: ExternalMetricStatus? = null
        private var objectMetricStatus: ObjectMetricStatus? = null
        private var pods: PodsMetricStatus? = null
        private var resource: ResourceMetricStatus? = null

        fun containerResource(block: ContainerResourceMetricStatus.Builder.() -> Unit) {
            containerResource = ContainerResourceMetricStatus.Builder().apply(block).build()
        }

        fun external(block: ExternalMetricStatus.Builder.() -> Unit) {
            external = ExternalMetricStatus.Builder().apply(block).build()
        }

        fun objectMetricStatus(block: ObjectMetricStatus.Builder.() -> Unit) {
            objectMetricStatus = ObjectMetricStatus.Builder().apply(block).build()
        }

        fun pods(block: PodsMetricStatus.Builder.() -> Unit) {
            pods = PodsMetricStatus.Builder().apply(block).build()
        }

        fun resource(block: ResourceMetricStatus.Builder.() -> Unit) {
            resource = ResourceMetricStatus.Builder().apply(block).build()
        }

        override fun build(): MetricStatus {
            return MetricStatus(
                type = vRequireNotNull(this::type),
                containerResource = containerResource,
                external = external,
                objectMetricStatus = objectMetricStatus,
                pods = pods,
                resource = resource
            )
        }
    }

    class Group : BuilderGroup<MetricStatus, Builder>(Builder()) {
        fun statuses(): List<MetricStatus>? = items()

        fun addMetricStatus(block: Builder.() -> Unit) {
            add(block)
        }
    }
}

