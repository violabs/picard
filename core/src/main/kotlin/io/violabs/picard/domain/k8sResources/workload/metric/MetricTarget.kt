package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class MetricTarget(
    val type: Type,
    val averageUtilization: Int? = null,
    val averageValue: Quantity? = null,
    val value: Quantity? = null
) {
    enum class Type {
        Utilization,
        Value,
        AverageValue
    }

    class Builder : DslBuilder<MetricTarget> {
        var type: Type? = null
        var averageUtilization: Int? = null
        private var averageValue: Quantity? = null
        private var value: Quantity? = null

        fun averageValue(value: String) {
            averageValue = Quantity(value)
        }

        fun value(value: String) {
            this.value = Quantity(value)
        }

        override fun build(): MetricTarget {
            return MetricTarget(
                type = vRequireNotNull(this::type),
                averageUtilization = averageUtilization,
                averageValue = averageValue,
                value = value
            )
        }
    }
}