package io.violabs.picard.v2.resources.workload.pod.container.action

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SleepAction describes a "sleep" action.
 */
@GeneratedDsl
data class SleepAction(
    /**
     * Seconds is the number of seconds to sleep.
     */
    val seconds: Long
)