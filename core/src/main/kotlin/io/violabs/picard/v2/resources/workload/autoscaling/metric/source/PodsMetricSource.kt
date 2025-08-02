package io.violabs.picard.v2.resources.workload.autoscaling.metric.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTarget

/**
 * PodsMetricSource indicates how to scale on a metric describing each pod in the current scale target
 * (for example, transactions-processed-per-second). The values will be averaged together before being
 * compared to the target value.
 */
@GeneratedDsl
data class PodsMetricSource(
    /**
     * metric identifies the target metric by name and selector
     */
    val metric: MetricIdentifier,
    /**
     * target specifies the target value for the given metric
     */
    val target: MetricTarget
)