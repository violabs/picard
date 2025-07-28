package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PriorityLevelConfigurationStatus represents the current state of a "request-priority".
 */
@GeneratedDsl
data class PriorityLevelConfigurationStatus(
    /**
     * conditions is the current state of "request-priority".
     * Patch strategy: merge on key type
     * Map: unique values on key type will be kept during a merge
     */
    val conditions: List<PriorityLevelConfigurationCondition>? = null
)