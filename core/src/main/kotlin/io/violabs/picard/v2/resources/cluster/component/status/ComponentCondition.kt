package io.violabs.picard.v2.resources.cluster.component.status

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Information about the condition of a component.
 *
 * conditions.status (string), required
 * Status of the condition for a component. Valid values for "Healthy": "True", "False", or "Unknown".
 *
 * conditions.type (string), required
 * Type of condition for a component. Valid value: "Healthy"
 *
 * conditions.error (string)
 * Condition error code for a component. For example, a health check error code.
 *
 * conditions.message (string)
 * Message about the condition for a component. For example, information about a health check.
 */
@GeneratedDsl(withListGroup = true)
data class ComponentCondition(
    /**
     * Status of the condition for a component. Valid values for "Healthy": "True", "False", or "Unknown".
     */
    val status: String,
    /**
     * Type of condition for a component. Valid value: "Healthy"
     */
    val type: String,
    /**
     * Condition error code for a component. For example, a health check error code.
     */
    @JsonProperty("error")
    val errorValue: String? = null,
    /**
     * Message about the condition for a component. For example, information about a health check.
     */
    val message: String? = null
)