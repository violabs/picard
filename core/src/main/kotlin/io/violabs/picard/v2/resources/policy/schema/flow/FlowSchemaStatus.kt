package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * FlowSchemaStatus represents the current state of a FlowSchema.
 */
@GeneratedDsl
data class FlowSchemaStatus(
    /**
     * conditions ([]FlowSchemaCondition)
     *
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * conditions is a list of the current states of FlowSchema.
     *
     * FlowSchemaCondition describes conditions for a FlowSchema.
     */
    val conditions: List<FlowSchemaCondition>? = null
)

