package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Local represents directly-attached storage with node affinity
 */
@GeneratedDsl
data class LocalVolumeSource(
    /**
     * path of the full path to the volume on the node. It can be either a directory
     * or block device (disk, partition, ...).
     */
    val path: String,
    /**
     * fsType is the filesystem type to mount. It applies only when the Path is a block
     * device. Must be a filesystem type supported by the host operating system. Ex.
     * "ext4", "xfs", "ntfs". The default value is to auto-select a filesystem if unspecified.
     *
     */
    val fsType: String? = null
)