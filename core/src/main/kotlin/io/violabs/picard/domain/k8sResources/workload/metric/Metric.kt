package io.violabs.picard.domain.k8sResources.workload.metric

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.Quantity

object Metric {
    data class Target(
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
    }

    data class Identifier(
        val name: String,
        val selector: LabelSelector? = null
    )

    data class Spec(
        val type: Type,
        val containerResource: ContainerResourceMetricSource? = null,
        val external: External.MetricSource? = null,
        val objectMetricSource: ObjectMetricSource? = null,
        val pods: PodsMetricSource? = null,
        val resource: ResourceMetricSource? = null
    ) {
    }

    data class Status(
        val type: Type,
        val containerResource: ContainerResourceMetricStatus? = null,
        val external: External.MetricStatus? = null,
        val objectMetricStatus: ObjectMetricStatus? = null,
        val pods: PodsMetricStatus? = null,
        val resource: ResourceMetricStatus? = null
    ) {

    }

    data class ValueStatus(
        val averageUtilization: Int? = null,
        val averageValue: Quantity? = null,
        val value: Quantity? = null
    )

    enum class Type {
        ContainerResource,
        External,
        Object,
        Pods,
        Resource
    }
}

