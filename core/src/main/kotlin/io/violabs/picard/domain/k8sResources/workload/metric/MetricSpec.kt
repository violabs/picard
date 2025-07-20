package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class MetricSpec(
    val type: MetricType,
    val containerResource: ContainerResourceMetricSource? = null,
    val external: ExternalMetricSource? = null,
    val objectMetricSource: ObjectMetricSource? = null,
    val pods: PodsMetricSource? = null,
    val resource: ResourceMetricSource? = null
) {
    class Builder : DslBuilder<MetricSpec> {
        var type: MetricType? = null
        private var containerResource: ContainerResourceMetricSource? = null
        private var external: ExternalMetricSource? = null
        private var objectMetricSource: ObjectMetricSource? = null
        private var pods: PodsMetricSource? = null
        private var resource: ResourceMetricSource? = null

        fun containerResource(block: ContainerResourceMetricSource.Builder.() -> Unit) {
            containerResource = ContainerResourceMetricSource.Builder().apply(block).build()
        }

        fun external(block: ExternalMetricSource.Builder.() -> Unit) {
            external = ExternalMetricSource.Builder().apply(block).build()
        }

        fun objectMetricSource(block: ObjectMetricSource.Builder.() -> Unit) {
            objectMetricSource = ObjectMetricSource.Builder().apply(block).build()
        }

        fun pods(block: PodsMetricSource.Builder.() -> Unit) {
            pods = PodsMetricSource.Builder().apply(block).build()
        }

        fun resource(block: ResourceMetricSource.Builder.() -> Unit) {
            resource = ResourceMetricSource.Builder().apply(block).build()
        }

        override fun build(): MetricSpec {
            return MetricSpec(
                type = vRequireNotNull(this::type),
                containerResource = containerResource,
                external = external,
                objectMetricSource = objectMetricSource,
                pods = pods,
                resource = resource
            )
        }
    }

    class Group : BuilderGroup<MetricSpec, Builder>(Builder()) {
        fun specs(): List<MetricSpec>? = items()

        fun metricSpec(block: Builder.() -> Unit) {
            add(block)
        }
    }
}