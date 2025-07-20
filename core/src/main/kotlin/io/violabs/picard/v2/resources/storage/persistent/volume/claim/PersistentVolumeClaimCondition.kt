package io.violabs.picard.v2.resources.configstorage.persistent.volume.claim

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.BooleanType

/**
 * Contains details about state of pvc
 */
@GeneratedDsl(withListGroup = true)
data class PersistentVolumeClaimCondition(
    /**
     * Status is the status of the condition. Can be True, False, Unknown.
     */
    val status: BooleanType,
    /**
     * Type is the type of the condition.
     */
    val type: String,
    /**
     * lastProbeTime is the time we probed the condition.
     */
    val lastProbeTime: String? = null,
    /**
     * lastTransitionTime is the time the condition transitioned from one status to another.
     */
    val lastTransitionTime: String? = null,
    /**
     * message is a human-readable message indicating details about the transition.
     */
    val message: String? = null,
    /**
     * reason is a unique, one-word, CamelCase reason for the condition's last transition.
     */
    val reason: String? = null
)