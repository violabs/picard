package io.violabs.picard.v2.resources.storage.volume.attachment

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * VolumeError captures an error encountered during a volume operation.
 */
@GeneratedDsl
data class VolumeError(
    /**
     * errorCode is a numeric gRPC code representing the error encountered during
     * Attach or Detach operations.
     *
     * This is an optional, alpha field that requires the MutableCSINodeAllocatableCount
     * feature gate being enabled to be set.
     */
    val errorCode: Int? = null,
    /**
     * message represents the error encountered during Attach or Detach operation. This string
     * may be logged, so it should not contain sensitive information.
     */
    val message: String? = null,
    /**
     * time represents the time the error was encountered.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val time: LocalDateTime? = null
)