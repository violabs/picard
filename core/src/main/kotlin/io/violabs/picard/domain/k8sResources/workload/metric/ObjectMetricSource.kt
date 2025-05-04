package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference

data class ObjectMetricSource(
    val describedObject: CrossVersionObjectReference,
    val metric: MetricIdentifier,
    val target: MetricTarget
) {
    class Builder : DSLBuilder<ObjectMetricSource> {
        private var describedObject: CrossVersionObjectReference? = null
        private var metric: MetricIdentifier? = null
        private var target: MetricTarget? = null

        fun describedObject(block: CrossVersionObjectReference.Builder.() -> Unit) {
            describedObject = CrossVersionObjectReference.Builder().apply(block).build()
        }

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        fun target(block: MetricTarget.Builder.() -> Unit) {
            target = MetricTarget.Builder().apply(block).build()
        }

        override fun build(): ObjectMetricSource {
            return ObjectMetricSource(
                describedObject = vRequireNotNull(this::describedObject),
                metric = vRequireNotNull(this::metric),
                target = vRequireNotNull(this::target)
            )
        }
    }
}