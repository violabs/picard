package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 * PriorityLevelConfigurationReference contains information that points
 * to the "request-priority" being used.
 */
@SingleEntryTransformDsl<String>(String::class)
data class PriorityLevelConfigurationReference(
    /**
     * priorityLevelConfiguration.name (string), required
     *
     * name is the name of the priority level configuration being referenced Required.
     */
    val name: String
)

