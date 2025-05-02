package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

object Metric {

    data class Spec(
        val type: Type,
        val containerResource: ContainerResourceMetricSource? = null,
        val external: ExternalMetricSource? = null,
        val objectMetricSource: ObjectMetricSource? = null,
        val pods: PodsMetricSource? = null,
        val resource: ResourceMetricSource? = null
    ) {
        class Builder : DSLBuilder<Spec> {
            var type: Type? = null
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

            override fun build(): Spec {
                return Spec(
                    type = requireNotNull(type),
                    containerResource = containerResource,
                    external = external,
                    objectMetricSource = objectMetricSource,
                    pods = pods,
                    resource = resource
                )
            }
        }

        class Group : BuilderGroup<Spec, Builder>(Builder()) {
            fun specs(): List<Spec>? = items()

            fun metricSpec(block: Builder.() -> Unit) {
                add(block)
            }
        }
    }

    data class Status(
        val type: Type,
        val containerResource: ContainerResourceMetricStatus? = null,
        val external: ExternalMetricStatus? = null,
        val objectMetricStatus: ObjectMetricStatus? = null,
        val pods: PodsMetricStatus? = null,
        val resource: ResourceMetricStatus? = null
    ) {
        class Builder : DSLBuilder<Status> {
            var type: Type? = null
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

            override fun build(): Status {
                return Status(
                    type = requireNotNull(type),
                    containerResource = containerResource,
                    external = external,
                    objectMetricStatus = objectMetricStatus,
                    pods = pods,
                    resource = resource
                )
            }
        }

        class Group : BuilderGroup<Status, Builder>(Builder()) {
            fun metrics(): List<Status>? = items()

            fun metric(block: Builder.() -> Unit) {
                add(block)
            }
        }
    }

    enum class Type {
        ContainerResource,
        External,
        Object,
        Pods,
        Resource
    }
}

