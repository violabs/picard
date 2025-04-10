package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.K8sEnum
import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.PodsMetricSource
import io.violabs.picard.domain.k8sResources.workload.pod.PodsMetricStatus
import io.violabs.picard.domain.k8sResources.workload.pod.container.BaseContainer
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerResourceMetricSource
import io.violabs.picard.domain.k8sResources.workload.pod.container.ContainerResourceMetricStatus

object Metric {
    data class Target(
        val type: Type,
        val averageUtilization: Int? = null,
        val averageValue: Quantity? = null,
        val value: Quantity? = null
    ) {
        enum class Type : K8sEnum {
            UTILIZATION,
            VALUE,
            AVERAGE_VALUE;

            override fun toString(): String = properCase()
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

    enum class Type : K8sEnum {
        CONTAINER_RESOURCE,
        EXTERNAL,
        OBJECT,
        PODS,
        RESOURCE;

        override fun toString(): String = properCase()
    }
}

