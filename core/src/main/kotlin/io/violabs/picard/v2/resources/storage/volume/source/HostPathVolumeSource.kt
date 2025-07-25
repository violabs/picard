package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Represents a pre-existing file or directory on the host machine that is directly
 * exposed to the container. This is generally used for system agents or other privileged
 * things that are allowed to see the host machine. Most containers will NOT need this.
 * More info: https://kubernetes.io/docs/concepts/storage/volumes#hostpath
 */
@GeneratedDsl
data class HostPathVolumeSource(
    /**
     * Path of the directory on the host. If the path is a symlink, it will follow the
     * link to the real path. More info: https://kubernetes.io/docs/concepts/storage/volumes#hostpath
     */
    val path: String,
    /**
     * Type for HostPath Volume Defaults to "" More info: https://kubernetes.io/docs/concepts/storage/volumes#hostpath
     */
    val type: String? = null
)