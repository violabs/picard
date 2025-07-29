package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.Instant

/**
 * CustomResourceDefinitionCondition contains details for the current condition of this pod.
 */
@GeneratedDsl(withListGroup = true)
data class CustomResourceDefinitionCondition(
    /**
     * status is the status of the condition. Can be True, False, Unknown.
     */
    val status: String? = null,
    /**
     * type is the type of the condition. Types include Established, NamesAccepted and Terminating.
     */
    val type: String? = null,
    /**
     * lastTransitionTime last time the condition transitioned from one status to another.
     */
    val lastTransitionTime: Instant? = null,
    /**
     * message is a human-readable message indicating details about last transition.
     */
    val message: String? = null,
    /**
     * reason is a unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null
)