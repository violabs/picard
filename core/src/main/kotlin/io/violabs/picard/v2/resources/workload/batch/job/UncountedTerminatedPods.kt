package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * UncountedTerminatedPods holds UIDs of Pods that have terminated but haven't been accounted in Job status counters.
 */
@GeneratedDsl
data class UncountedTerminatedPods(
    /**
     * failed holds UIDs of failed Pods.
     */
    val failed: List<String>? = null,
    /**
     * succeeded holds UIDs of succeeded Pods.
     */
    val succeeded: List<String>? = null
)