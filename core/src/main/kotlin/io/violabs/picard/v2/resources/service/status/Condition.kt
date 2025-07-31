package io.violabs.picard.v2.resources.service.status

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

@GeneratedDsl(withListGroup = true)
data class Condition(
    /**
     * lastTransitionTime is the last time the condition transitioned from one status to another.
     */
    val lastTransitionTime: LocalDateTime,
    
    /**
     * message is a human readable message indicating details about the transition.
     */
    val message: String,
    
    /**
     * reason contains a programmatic identifier indicating the reason for the condition's last transition.
     */
    val reason: String,
    
    /**
     * status of the condition, one of True, False, Unknown.
     */
    val status: String,
    
    /**
     * type of condition in CamelCase or in foo.example.com/CamelCase.
     */
    val type: String,
    
    /**
     * observedGeneration represents the .metadata.generation that the condition was set based upon.
     */
    val observedGeneration: Long? = null
)