package io.violabs.picard.v2.resources.storage.volume.projection

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.storage.volume.DownwardApiVolumeFile

/**
 * Information about the downwardAPI data to project
 *
 * Represents downward API info for projecting into a projected volume.
 * Note that this is identical to a downwardAPI volume source without the default mode.
 */
@GeneratedDsl
data class DownwardApiProjection(
    /**
     * Atomic: will be replaced during a merge
     *
     * Items is a list of DownwardAPIVolume file
     */
    val items: List<DownwardApiVolumeFile>? = null
)