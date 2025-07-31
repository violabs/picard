package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * NamespaceCondition contains details about state of namespace.
 */
@GeneratedDsl(withListGroup = true)
data class NamespaceCondition(
    /**
     * Status of the condition, one of True, False, Unknown.
     */
    val status: String,
    /**
     * Type of namespace controller condition.
     */
    val type: String,
    /**
     * Last time the condition transitioned from one status to another.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * Human-readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * Unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null
)