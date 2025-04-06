package io.violabs.picard.domain.k8sResources.workload

object External {
    data class MetricSource(
        val metric: Metric.Identifier,
        val target: Metric.Target
    )

    data class MetricStatus(
        val current: Metric.ValueStatus,
        val metric: Metric.Identifier
    )
}