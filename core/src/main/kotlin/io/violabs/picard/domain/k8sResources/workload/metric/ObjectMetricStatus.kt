package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference

data class ObjectMetricStatus(
    val current: MetricValueStatus,
    val describedObject: CrossVersionObjectReference,
    val metric: MetricIdentifier,
    val pods: PodsMetricSource
) {
    class Builder : DSLBuilder<ObjectMetricStatus> {
        private var current: MetricValueStatus? = null
        private var describedObject: CrossVersionObjectReference? = null
        private var metric: MetricIdentifier? = null
        private var pods: PodsMetricSource? = null

        fun current(block: MetricValueStatus.Builder.() -> Unit) {
            current = MetricValueStatus.Builder().apply(block).build()
        }

        fun describedObject(block: CrossVersionObjectReference.Builder.() -> Unit) {
            describedObject = CrossVersionObjectReference.Builder().apply(block).build()
        }

        fun metric(block: MetricIdentifier.Builder.() -> Unit) {
            metric = MetricIdentifier.Builder().apply(block).build()
        }

        fun pods(block: PodsMetricSource.Builder.() -> Unit) {
            pods = PodsMetricSource.Builder().apply(block).build()
        }

        override fun build(): ObjectMetricStatus {
            return ObjectMetricStatus(
                current = vRequireNotNull(this::current),
                describedObject = vRequireNotNull(this::describedObject),
                metric = vRequireNotNull(this::metric),
                pods = vRequireNotNull(this::pods)
            )
        }
    }
}