package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * PersistentVolumeStatus is the current status of a persistent volume.
 */
@GeneratedDsl
data class PersistentVolumeStatus(
    /**
     * lastPhaseTransitionTime is the time the phase transitioned from one to another and automatically
     * resets to current time everytime a volume phase transitions.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers
     * are provided for many of the factory methods that the time package offers.
     */
    val lastPhaseTransitionTime: LocalDateTime? = null,
    /**
     * message is a human-readable message indicating details about why the volume is in this state.
     */
    val message: String? = null,
    /**
     * phase indicates if a volume is available, bound to a claim, or released by a claim.
     * More info: https://kubernetes.io/docs/concepts/storage/persistent-volumes#phase
     */
    val phase: String? = null,
    /**
     * reason is a brief CamelCase string that describes any failure and is meant
     * for machine parsing and tidy display in the CLI.
     */
    val reason: String? = null
)