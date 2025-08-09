package io.violabs.picard.v2.resources.workload.pod.gate

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * SchedulingGate is associated to a Pod to guard its scheduling.
 */
@GeneratedDsl(withListGroup = true)
data class SchedulingGate(
    /**
     * Name of the scheduling gate. Each scheduling gate must have a unique name field.
     */
    val name: String
)