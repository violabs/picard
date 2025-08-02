package io.violabs.picard.v2.resources.workload.autoscaling.metric

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

/**
 * MetricIdentifier defines the name and optionally selector for a metric
 */
@GeneratedDsl
data class MetricIdentifier(
    /**
     * name is the name of the given metric
     */
    val name: String,
    /**
     * selector is the string-encoded form of a standard kubernetes label selector for the given metric 
     * When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. 
     * When unset, just the metricName will be used to gather metrics.
     */
    val selector: LabelSelector? = null
)