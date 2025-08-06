package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.storage.volume.projection.VolumeProjection

/**
 * Represents a projected volume source
 */
@GeneratedDsl
data class ProjectedVolumeSource(
    /**
     * projected.defaultMode (int32)
     *
     * defaultMode are the mode bits used to set permissions on created files by default.
     * Must be an octal value between 0000 and 0777 or a decimal value between 0 and 511.
     * YAML accepts both octal and decimal values, JSON requires decimal values for mode bits.
     * Directories within the path are not affected by this setting. This might be in conflict
     * with other options that affect the file mode, like fsGroup, and the result can be
     * other mode bits set.
     */
    val defaultMode: Int? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * The list of volume projections. Each entry in this list handles one source.
     *
     * Projection that may be projected along with other supported volume types. Exactly one of these fields must be set.
     */
    val sources: List<VolumeProjection>
)