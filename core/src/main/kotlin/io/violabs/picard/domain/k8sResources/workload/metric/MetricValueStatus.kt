package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class MetricValueStatus(
    val averageUtilization: Int? = null,
    val averageValue: Quantity? = null,
    val value: Quantity? = null
) {
    class Builder : DslBuilder<MetricValueStatus> {
        var averageUtilization: Int? = null
        private var averageValue: Quantity? = null
        private var value: Quantity? = null

        fun averageValue(value: String) {
            averageValue = Quantity(value)
        }

        fun value(value: String) {
            this.value = Quantity(value)
        }

        override fun build(): MetricValueStatus {
            return MetricValueStatus(
                averageUtilization = averageUtilization,
                averageValue = averageValue,
                value = value
            )
        }
    }
}