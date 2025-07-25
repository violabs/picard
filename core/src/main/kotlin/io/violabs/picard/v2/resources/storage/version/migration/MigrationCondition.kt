package io.violabs.picard.v2.resources.storage.version.migration

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * Describes the state of a migration at a certain point.
 */
@GeneratedDsl(withListGroup = true)
data class MigrationCondition(
    /**
     * conditions.status (string), required
     *
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * conditions.type (string), required
     *
     * Type of the condition.
     */
    val type: String,
    /**
     * conditions.lastUpdateTime (Time)
     *
     * The last time this condition was updated.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.
     */
    val lastUpdateTime: LocalDateTime? = null,
    /**
     * conditions.message (string)
     *
     * A human readable message indicating details about the transition.
     */
    val message: String? = null,
    /**
     * conditions.reason (string)
     *
     * The reason for the condition's last transition.
     */
    val reason: String? = null
)